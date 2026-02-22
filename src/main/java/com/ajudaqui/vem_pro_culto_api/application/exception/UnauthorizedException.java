package com.ajudaqui.vem_pro_culto_api.application.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String mensagem) {
    super(mensagem);
  }

}
