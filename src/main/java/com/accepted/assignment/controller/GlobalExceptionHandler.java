package com.accepted.assignment.controller;

import com.accepted.assignment.exception.ExistingMatchOddException;
import com.accepted.assignment.exception.InvalidOddException;
import com.accepted.assignment.exception.NotFoundException;
import com.accepted.assignment.exception.SameTeamMatchException;
import com.accepted.assignment.exception.UnsupportedSportException;
import com.accepted.assignment.exception.model.Error;
import com.accepted.assignment.exception.model.ErrorResponse;
import com.accepted.assignment.exception.model.InnerError;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(UnsupportedSportException.class)
  public ResponseEntity<ErrorResponse> handleUnsupportedSportException(UnsupportedSportException ex) {
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

  @ExceptionHandler(ExistingMatchOddException.class)
  public ResponseEntity<ErrorResponse> handleExistingMatchOddException(ExistingMatchOddException ex) {
    log.error(ex.getMessage(),ex);

    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(
            ErrorResponse.builder().error(
                    Error.builder()
                        .code(HttpStatus.CONFLICT.name())
                        .message(ex.getMessage())
                        .innerError(
                            InnerError.builder()
                                .type(ex.getClass().getSimpleName())
                                .date(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE))
                                .build())
                        .build())
                .build());
  }

  @ExceptionHandler(InvalidOddException.class)
  public ResponseEntity<ErrorResponse> handleInvalidOddException(InvalidOddException ex) {
    log.error(ex.getMessage(),ex);

    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
        .body(
            ErrorResponse.builder().error(
                    Error.builder()
                        .code(HttpStatus.NOT_ACCEPTABLE.name())
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
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
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


  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
    Throwable cause = ex.getCause();
    if (cause instanceof UnsupportedSportException use) {
      log.error(use.getMessage(), use);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(
              ErrorResponse.builder().error(
                      Error.builder()
                          .code(HttpStatus.BAD_REQUEST.name())
                          .message(use.getMessage())
                          .innerError(
                              InnerError.builder()
                                  .type(use.getClass().getSimpleName())
                                  .date(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE))
                                  .build())
                          .build())
                  .build());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.builder().error(
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

}
