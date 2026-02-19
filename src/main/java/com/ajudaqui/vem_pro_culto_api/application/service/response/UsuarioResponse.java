package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import lombok.Getter;

@Getter
public class UsuarioResponse {


  private Long id;
  private String nome;
  private String email;
  private Boolean ativo;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

    public UsuarioResponse(Usuario usuario) {
      this.id = usuario.getId();
      this.nome = usuario.getNome();
      this.email = usuario.getEmail();
      this.ativo = usuario.getAtivo();
      this.endereco = usuario.getEndereco();
      this.telefone = usuario.getTelefone();
      this.redesSociais = usuario.getRedesSociais();
    }

}

