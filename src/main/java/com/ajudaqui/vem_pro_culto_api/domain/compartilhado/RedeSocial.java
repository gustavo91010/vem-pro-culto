package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Embeddable
@AllArgsConstructor
public class RedeSocial {

  private String url;

  private String tipo;
}

