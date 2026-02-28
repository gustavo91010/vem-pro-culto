package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import lombok.Data;

@Data
public class AtividadeResponse {

  private EAtividadeTipo tipo;
  private String descricao;
  private LocalDateTime horario;

  public AtividadeResponse(Atividade atividade) {
    this.tipo = atividade.getTipo();
    this.descricao = atividade.getDescricao();
    this.horario = atividade.getHorario();
  }
}
