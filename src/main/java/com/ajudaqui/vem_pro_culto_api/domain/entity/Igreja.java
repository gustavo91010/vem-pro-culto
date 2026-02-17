package com.ajudaqui.vem_pro_culto_api.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.*;

public class Igreja {

  
  private Long id;

  private String nomeFantasia;

  private String razaoSocial;

  private String email;

  private String senha;

  private List<Endereco> endereco;

  private List<Telefone> telefone;

  private List<RedeSocial> redesSociais;

  private String cnpj;

  private LocalDateTime atualizadoEm;

  private LocalDateTime registradoEm;

}
