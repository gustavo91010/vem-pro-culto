package com.ajudaqui.vem_pro_culto_api.domain.entity.atividade;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import lombok.*;

@Getter
@Setter
@Builder
public class Atividade {

  private Igreja igreja;
  private EAtividadeTipo tipo;
  private String descricao;
  private LocalDateTime horario;
  private LocalDateTime registradoEm;
  private boolean ativo;


  
}
