package com.ajudaqui.vem_pro_culto_api.web.eception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

  private LocalDateTime timestamp;
  private String message;
  private String details;
  private int status;
  private List<String> developerMessage;

  public ErrorDetails(Exception ex, int status) {
    this.status = status;
    this.timestamp = LocalDateTime.now();
    this.message = ex.getMessage();
    this.details = ex.getLocalizedMessage();
    this.developerMessage = Arrays.asList(ex.getClass().getName(), ex.toString());
  }

  // ErrorDetails errorDetails = ErrorDetails.builder()
  // .timestamp(LocalDateTime.now())
  // .status(HttpStatus.BAD_REQUEST.value())
  // .message(ex.getMessage())
  // .details(ex.getLocalizedMessage())
  // .developerMessage(Arrays.asList(ex.getClass().getName(), ex.toString()))
  // .build();
}
