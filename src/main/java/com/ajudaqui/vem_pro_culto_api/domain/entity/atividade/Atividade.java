package com.ajudaqui.vem_pro_culto_api.domain.entity.atividade;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Atividade {

  private Long igrejaId;
  private EAtividadeTipo tipo;
  private String descricao;
  private LocalDateTime horario;


  
}
