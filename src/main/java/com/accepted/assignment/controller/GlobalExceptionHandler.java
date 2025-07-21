package com.accepted.assignment.controller;

import com.accepted.assignment.exception.NotFoundException;
import com.accepted.assignment.exception.SameTeamMatchException;
import com.accepted.assignment.exception.model.Error;
import com.accepted.assignment.exception.model.ErrorResponse;
import com.accepted.assignment.exception.model.InnerError;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex){
    log.error(ex.getMessage(),ex);

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(
            ErrorResponse.builder().error(
                    Error.builder()
                        .code(HttpStatus.NOT_FOUND.name())
                        .message(ex.getMessage())
                        .innerError(
                            InnerError.builder()
                                .type(ex.getClass().getSimpleName())
                                .date(ZonedDateTime.now().format(
                                    DateTimeFormatter.ISO_DATE))
                                .build())
                        .build())
                .build());
  }

  @ExceptionHandler(SameTeamMatchException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(SameTeamMatchException ex) {
    log.error(ex.getMessage(),ex);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            ErrorResponse.builder().error(
                    Error.builder()
                        .code(HttpStatus.BAD_REQUEST.name())
                        .message(ex.getMessage())
                        .innerError(
                            InnerError.builder()
                                .type(ex.getClass().getSimpleName())
                                .date(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE))
                                .build())
                        .build())
                .build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException (Exception ex) {
    log.error(ex.getMessage(),ex);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            ErrorResponse.builder().error(
                    Error.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .message(ex.getMessage())
                        .innerError(
                            InnerError.builder()
                                .type(ex.getClass().getSimpleName())
                                .date(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE))
                                .build())
                        .build())
                .build());
  }

}
