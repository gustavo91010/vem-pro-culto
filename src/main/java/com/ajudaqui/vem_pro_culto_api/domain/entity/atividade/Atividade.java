package com.ajudaqui.vem_pro_culto_api.domain.entity.atividade;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Atividade {

  private Long id;
  private Long igrejaId;
  private String nomeIgreja;
  private EAtividadeTipo tipo;
  private String descricao;
  private LocalDateTime horario;


  
}
