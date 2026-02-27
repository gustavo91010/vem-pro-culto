package com.ajudaqui.vem_pro_culto_api.application.service.dto;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.application.exception.BadRequestException;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AtividadeDTO {

  private Long igrejaId;
  private EAtividadeTipo tipo;
  private String descricao;
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime horario;

  public AtividadeDTO(Long igrejaId, String tipo, String descricao, LocalDateTime horario) {
    this.igrejaId = igrejaId;
    this.tipo = EAtividadeTipo.valueOf(tipo);
    if (descricao.isBlank())
      throw new BadRequestException("Descrição não pode estar vazio.");
    this.descricao = descricao;
    this.horario = horario;
  }

  public Atividade toModel() {
    return Atividade.builder()
        .igrejaId(this.igrejaId)
        .tipo(this.tipo)
        .horario(this.horario)
        .descricao(this.descricao)
        .build();
  }

}
