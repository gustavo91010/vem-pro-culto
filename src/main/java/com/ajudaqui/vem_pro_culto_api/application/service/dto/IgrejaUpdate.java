package com.ajudaqui.vem_pro_culto_api.application.service.dto;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;

import lombok.Data;

@Data
public class IgrejaUpdate {

  private String nomeFantasia;
  private String email;
  private String cnpj;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;
}
