package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import com.ajudaqui.vem_pro_culto_api.domain.enums.ETipoRedeSocial;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RedeSocial {

  private String url;

  @Enumerated(EnumType.STRING)
  private ETipoRedeSocial tipo;
}
