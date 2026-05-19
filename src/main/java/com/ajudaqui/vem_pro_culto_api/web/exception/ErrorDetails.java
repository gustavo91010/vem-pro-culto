package com.ajudaqui.vem_pro_culto_api.web.exception;

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

    String fullMessage = ex.getMessage() != null ? ex.getMessage() : "";

    if (fullMessage.contains("Detalhe")) {
      String[] parts = fullMessage.split("Detalhe");
      this.message = parts[0].replace(":", "").trim();
      this.details = parts.length > 1 ? parts[1].replace(":", "").trim() : "";
    } else {
      this.message = fullMessage;
      this.details = "Sem detalhes adicionais.";
    }

    this.developerMessage = Arrays.asList(ex.getClass().getName(), ex.toString());
  }

}
