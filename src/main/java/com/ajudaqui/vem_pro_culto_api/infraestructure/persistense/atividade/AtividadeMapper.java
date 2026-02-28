package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;

public class AtividadeMapper {

  public Atividade toModel(AtividadeEntity entity) {

    return Atividade.builder()
        .igrejaId(entity.getIgrejaId())
        .descricao(entity.getDescricao())
        .tipo(entity.getTipo())
        .horario(entity.getHorario())
        .build();
  }

  public AtividadeEntity toEntity(Atividade model) {

    return AtividadeEntity.builder()
        .igrejaId(model.getIgrejaId())
        .descricao(model.getDescricao())
        .tipo(model.getTipo())
        .horario(model.getHorario())
        .build();
  }
}
