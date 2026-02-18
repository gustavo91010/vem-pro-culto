package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import com.ajudaqui.vem_pro_culto_api.domain.enums.ETipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Telefone {

  private String numero;

  private ETipoTelefone tipo;

}
