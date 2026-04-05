package com.ajudaqui.vem_pro_culto_api.web.exception;

import java.util.Arrays;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> exception(Exception ex) {

    infoTrace(ex);
    ErrorDetails errorDetails = new ErrorDetails(ex, HttpStatus.BAD_REQUEST.value());

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<ErrorDetails> notFoundException(NotFoundException ex) {

    infoTrace(ex);
    ErrorDetails errorDetails = new ErrorDetails(ex, 404);

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ErrorDetails> badRequestException(BadRequestException ex) {

    infoTrace(ex);
    ErrorDetails errorDetails = new ErrorDetails(ex, 400);

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  // private void infoTrace(Exception exception) {
  // StackTraceElement element = exception.getStackTrace()[0];
  // StackTraceElement callElement = exception.getStackTrace()[1];

  // log.error("Exception occurred at: [{}] {}, line: {} with error Details: [{}]
  // {}, line: {} | {}",
  // callElement.getFileName(), callElement.getMethodName(),
  // callElement.getLineNumber(), element.getFileName(), element.getMethodName(),
  // element.getLineNumber(), exception.getMessage());
  // }

  private void infoTrace(Exception exception) {
    StackTraceElement[] fullTrace = exception.getStackTrace();

    List<StackTraceElement> projectTrace = Arrays.stream(fullTrace)
        .filter(e -> e.getClassName().startsWith("br.com.gif"))
        .toList();

    StringBuilder sb = new StringBuilder("Project stack trace:\n");
    for (int i = 0; i < projectTrace.size(); i++) {
      StackTraceElement e = projectTrace.get(i);
      sb.append(String.format("  [%d] %s.%s (line %d)%n",
          i, e.getFileName(), e.getMethodName(), e.getLineNumber()));
    }
    sb.append("Message: ").append(exception.getMessage());

    log.error(sb.toString());
  }

}
