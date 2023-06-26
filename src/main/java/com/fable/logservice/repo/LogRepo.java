package com.fable.logservice.repo;

import com.fable.logservice.repo.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LogRepo extends CrudRepository<Log, Long> {

    Optional<Log> findByLogId(Long logId);

    void deleteByLogId(Long logId);
}
