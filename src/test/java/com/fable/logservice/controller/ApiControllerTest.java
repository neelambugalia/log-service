package com.fable.logservice.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.fable.logservice.repo.model.Log;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiControllerTest {

    private static final String API_ROOT = "http://localhost:9090/api/logs";

    // GET all
    @Test
    public void whenGetAll_thenOK() {
        final Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    // GET by id
    @Test
    public void whenGetById_thenOK() {
        final Log log = createRandomLog();
        final String url = createLogAsUri(log);

        final Response response = RestAssured.get(url);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(log, response.body().as(Log.class));
    }

    // GET NOT_FOUND
    @Test
    public void whenGetNotExistById_thenNotFound() {
        final Response response = RestAssured.get(API_ROOT + "/2");
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST
    @Test
    public void whenCreateNew_thenCreated() {
        final Log log = createRandomLog();

        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(log)
                .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    // DELETE
    @Test
    public void whenDeleteById_thenOk() {
        final Log log = createRandomLog();
        final String url = createLogAsUri(log);

        Response response = RestAssured.delete(url);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(url);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // ===============================

    private Log createRandomLog() {
        return new Log(1L, 1, randomAlphabetic(5), "CREATE_USER", 50);
    }

    private String createLogAsUri(Log log) {
        final Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(log)
                .post(API_ROOT);

        System.out.println(response.body().as(Log.class));
        return API_ROOT + "/" + log.getId();
    }

}