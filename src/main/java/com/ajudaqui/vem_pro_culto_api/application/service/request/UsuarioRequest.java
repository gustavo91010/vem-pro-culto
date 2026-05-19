package com.ajudaqui.vem_pro_culto_api.application.service.request;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequest {

  @JsonProperty("access_token")
  private String authToken;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

  public UsuarioRequest(String authToken, Endereco endereco,
      List<Telefone> telefone, List<RedeSocial> redesSociais) {
    this.authToken = authToken;
    this.endereco = endereco;
    this.telefone = telefone;
    this.redesSociais = redesSociais;
  }

}
