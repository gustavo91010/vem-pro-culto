package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeView;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AtividadeMapper {

  public Atividade toModel(AtividadeEntity entity) {

    return Atividade.builder()
        .id(entity.getId())
        .igrejaId(entity.getIgrejaId())
        .nomeIgreja("")
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

  public List<Atividade> fromView(List<AtividadeView> listaAtividade) {
    return listaAtividade.stream()
        .map(v -> Atividade.builder()
            .id(v.getId())
            .igrejaId(v.getIgrejaId())
            .nomeIgreja(v.getNomeIgreja())
            .tipo(EAtividadeTipo.valueOf(v.getTipo()))
            .descricao(v.getDescricao())
            .horario(v.getHorario())
            .build())
        .toList();
  }
}
