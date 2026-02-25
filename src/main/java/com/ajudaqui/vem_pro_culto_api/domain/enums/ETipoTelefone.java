package com.ajudaqui.vem_pro_culto_api.domain.enums;

import com.ajudaqui.vem_pro_culto_api.domain.exception.TipoTelefoneInvalidoException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ETipoTelefone {
  CELULAR, WHATSAPP, FIXO;

  @JsonCreator
  public static ETipoTelefone fromValue(String value) {
    for (ETipoTelefone tipo : ETipoTelefone.values()) {
      if (tipo.name().equalsIgnoreCase(value)) {
        return tipo;
      }
    }
    throw new TipoTelefoneInvalidoException("Tipo de telefone inválido: " + value);
  }
}
