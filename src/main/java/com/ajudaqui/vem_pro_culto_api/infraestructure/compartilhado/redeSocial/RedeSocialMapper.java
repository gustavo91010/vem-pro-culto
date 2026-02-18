package com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.redeSocial;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;

import org.springframework.stereotype.Component;

@Component
public class RedeSocialMapper {

  public RedeSocialComp toEntity(RedeSocial redeSocial) {
    return new RedeSocialComp(redeSocial.getUrl(), redeSocial.getTipo());
  }

  public RedeSocial toModel(RedeSocialComp redeSocial) {

    return new RedeSocial(redeSocial.getUrl(), redeSocial.getTipo());
  }
}
