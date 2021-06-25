package com.spring.boec.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest request) {
    BaseResponse response =
            new BaseResponse(
                    null,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    ((ServletWebRequest) request).getRequest().getServletPath());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}