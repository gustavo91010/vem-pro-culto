package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import com.ajudaqui.vem_pro_culto_api.domain.enums.ETipoTelefone;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Telefone {

  private String numero;

  private ETipoTelefone tipo;

}
