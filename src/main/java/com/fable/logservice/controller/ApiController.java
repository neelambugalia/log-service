package com.fable.logservice.controller;

import com.fable.logservice.exception.IdMismatchException;
import com.fable.logservice.exception.LogNotFoundException;
import com.fable.logservice.repo.LogRepo;
import com.fable.logservice.repo.model.Log;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private LogRepo repo;

    @GetMapping("/logs")
    public Iterable<Log> findAll() {
        logger.info("Received get logs req.");
        return repo.findAll();
    }

    @GetMapping("/logs/{id}")
    public Log findByTitle(@PathVariable Long id) {
        logger.info("Received get log req: {}", id);
        return repo.findByLogId(id).orElseThrow(LogNotFoundException::new);
    }

    @PostMapping(value = "/logs", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Log create(@RequestBody Log log) {
        logger.info("Received create log req: {}", log);
        final Optional<Log> logOpt = repo.findByLogId(log.getLogId());
        return logOpt.orElseGet(() -> repo.save(log));
    }

    @DeleteMapping("logs/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Received delete log req: {}", id);
        Log log = repo.findByLogId(id).orElseThrow(LogNotFoundException::new);
        repo.deleteById(log.getId());
    }

    @PutMapping("logs/{id}")
    public Log update(@RequestBody Log log, @PathVariable Long id) {
        logger.info("Received put log req: {}", log);
        if (log.getLogId() != id) {
            throw new IdMismatchException();
        }
        repo.findByLogId(id)
                .orElseThrow(LogNotFoundException::new);
        return repo.save(log);
    }
}

