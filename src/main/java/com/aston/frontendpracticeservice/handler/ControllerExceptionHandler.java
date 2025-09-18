package com.aston.frontendpracticeservice.handler;

import com.aston.frontendpracticeservice.domain.response.SimpleMessage;
import com.aston.frontendpracticeservice.exception.auth.PuppetAuthException;
import com.aston.frontendpracticeservice.exception.business.PuppetBusinessException;
import com.aston.frontendpracticeservice.exception.server.PuppetServerErrorException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Map<Class<? extends Exception>, HttpStatus> statusMap = Map.of(
            PuppetAuthException.class, HttpStatus.UNAUTHORIZED,
            PuppetBusinessException.class, HttpStatus.BAD_REQUEST,
            PuppetServerErrorException.class, HttpStatus.INTERNAL_SERVER_ERROR,
            TypeMismatchException.class, HttpStatus.BAD_REQUEST);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleMessage> handle(Exception e) {
        var status = statusMap.entrySet().stream()
                .filter(entry -> entry.getKey().isInstance(e))
                .map(Map.Entry::getValue)
                .findFirst().orElse(HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(status).body(new SimpleMessage(e.getMessage()));
    }
}
