package com.ajudaqui.vem_pro_culto_api.application.service.response;

import lombok.Getter;

@Getter
public class ResponseMessage {

  private String mensagem;

  public ResponseMessage(String mensagem) {
    this.mensagem = mensagem;
  }
}
