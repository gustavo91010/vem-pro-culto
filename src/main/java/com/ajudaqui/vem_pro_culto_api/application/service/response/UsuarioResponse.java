package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {

  private UUID authToken;
  private Boolean ativo;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

  public UsuarioResponse(Usuario usuario) {
    this.authToken = usuario.getAuthToken();
    this.ativo = usuario.getAtivo();
    this.endereco = usuario.getEndereco();
    this.telefone = usuario.getTelefone();
    this.redesSociais = usuario.getRedesSociais();
  }

}
