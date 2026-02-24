package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

import lombok.Data;

@Data
public class IgrejaResponse {

  private Long id;
  private String nomeFantasia;
  private String razaoSocial;
  private String email;
  private String cnpj;
  private boolean ativo;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

  public IgrejaResponse(Igreja igreja) {
    igreja.getUsuarios().forEach(i -> {
      System.out.printf("A igreja id %d tem o relaciodnament de %s com o usuario id %d\n",
          i.getIgreja().getId(), i.getPapel().name(), i.getUsuario().getId());
    });

    this.id = igreja.getId();
    this.nomeFantasia = igreja.getNomeFantasia();
    this.razaoSocial = igreja.getRazaoSocial();
    this.nomeFantasia = igreja.getNomeFantasia();
    this.email = igreja.getEmail();
    this.cnpj = igreja.getCnpj();
    this.ativo = igreja.getAtivo();
    this.endereco = igreja.getEndereco();
    this.telefone = igreja.getTelefone();
    this.redesSociais = igreja.getRedesSociais();
  }

}
