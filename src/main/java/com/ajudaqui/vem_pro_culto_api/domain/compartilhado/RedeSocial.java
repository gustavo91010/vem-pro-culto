package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RedeSocial {

  private String url;

  private String tipo;
}

