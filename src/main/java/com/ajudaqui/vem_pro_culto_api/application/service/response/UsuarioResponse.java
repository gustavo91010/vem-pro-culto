package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import lombok.Data;

@Data
public class UsuarioResponse {

  private Long id;
  private UUID authToken;
  private Boolean ativo;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;
  private Set<Long> igrejasFavoritas;

  public UsuarioResponse(Usuario usuario) {
    this.id = usuario.getId();
    this.authToken = usuario.getAuthToken();
    this.ativo = usuario.getAtivo();
    this.endereco = usuario.getEndereco();
    this.telefone = usuario.getTelefone();
    this.redesSociais = usuario.getRedesSociais();
  }

  public UsuarioResponse(Usuario usuario, Set<Long> igrejasFavoritas) {
    this.id = usuario.getId();
    this.authToken = usuario.getAuthToken();
    this.ativo = usuario.getAtivo();
    this.endereco = usuario.getEndereco();
    this.telefone = usuario.getTelefone();
    this.redesSociais = usuario.getRedesSociais();
    this.igrejasFavoritas = igrejasFavoritas;
  }
}
