package com.ajudaqui.vem_pro_culto_api.application.service.request;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioRequest {

  
  private String nome;
  private String email;
  private String senha;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

}
