package com.ajudaqui.vem_pro_culto_api.domain.enums;

import com.ajudaqui.vem_pro_culto_api.application.exception.TipoInvalidoExceptione;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ETipoRedeSocial {
  SITE, YOUTUBE, INSTAGRAM, FACEBOOK, TWITTER, TIKTOK;

  @JsonCreator
  public static ETipoRedeSocial fromValue(String value) {
    for (ETipoRedeSocial tipo : ETipoRedeSocial.values()) {
      if (tipo.name().equalsIgnoreCase(value)) {
        return tipo;
      }
    }
    throw new TipoInvalidoExceptione("Tipo de rede social invalido: " + value);
  }
}
