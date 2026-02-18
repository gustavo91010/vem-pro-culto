package com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.telefone;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;

import org.springframework.stereotype.Component;

@Component
public class TelefoneMapper {

  public TelefoneComp toEntity(Telefone telefone) {
    return new TelefoneComp(telefone.getNumero(), telefone.getTipo());
  }

  public Telefone toModel(TelefoneComp telefone) {
    return new Telefone(telefone.getNumero(), telefone.getTipo());
  }

}
