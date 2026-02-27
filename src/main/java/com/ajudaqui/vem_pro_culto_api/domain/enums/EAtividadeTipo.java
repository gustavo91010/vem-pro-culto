package com.ajudaqui.vem_pro_culto_api.domain.enums;

import com.ajudaqui.vem_pro_culto_api.application.exception.TipoInvalidoExceptione;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum EAtividadeTipo {
  CULTO, ATIVIDADE, EVANGELISMO, ACAO_SOCIAL, EBD;

  @JsonCreator
  public static EAtividadeTipo fromValue(String value) {
    for (EAtividadeTipo tipo : EAtividadeTipo.values()) {
      if (tipo.name().equalsIgnoreCase(value)) {
        return tipo;
      }
    }
    throw new TipoInvalidoExceptione ("Tipo de atividade inválido: " + value);
  }
}
