package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;

import lombok.Data;

@Data
public class AtividadeResponseList {

  private List<AtividadeResponse> atividades;

  public AtividadeResponseList(List<Atividade> atividade) {
    this.atividades = atividade.stream()
        .map(AtividadeResponse::new)
        .toList();
  }

}
