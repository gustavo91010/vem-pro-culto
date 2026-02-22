package com.ajudaqui.vem_pro_culto_api.application.service.response;

import lombok.Data;

@Data
public class StatusResponse {
  private String mensagem;
  private Boolean ativo;

  public StatusResponse(Boolean ativo,String mensagem) {
    this.mensagem = mensagem;
    this.ativo = ativo;
  }

}
