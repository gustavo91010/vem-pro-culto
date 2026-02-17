package com.ajudaqui.vem_pro_culto_api.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.*;

public class Usuario {

  
  private Long id;

  private String nome;

  private String email;

  private String senha;

  private LocalDateTime atualizadoEm;

  private LocalDateTime registradoEm;

  private List<Endereco> endereco;

  private List<Telefone> telefone;

  private List<RedeSocial> redesSociais;
}
