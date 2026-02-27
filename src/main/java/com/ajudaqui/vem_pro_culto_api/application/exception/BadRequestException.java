package com.ajudaqui.vem_pro_culto_api.application.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String mensagem) {
    super(mensagem);
  }
}
