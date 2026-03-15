package com.ajudaqui.vem_pro_culto_api.application.service.request;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRequest {

  private String nome;
  private String email;
  private String authToken;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

  public UsuarioRequest(String nome, String email, String senha, String authToken, Endereco endereco,
      List<Telefone> telefone, List<RedeSocial> redesSociais) {
    this.nome = nome;
    this.email = email;
    this.authToken = authToken;
    this.endereco = endereco;
    this.telefone = telefone;
    this.redesSociais = redesSociais;
  }

  public void setNome(String nome) {
    if (nome.isBlank())
      nome = this.email;
    this.nome = nome;
  }

}
