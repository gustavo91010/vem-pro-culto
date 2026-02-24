package com.ajudaqui.vem_pro_culto_api.domain.entity.igreja;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Igreja {

  private Long id;
  private String nomeFantasia;
  private String razaoSocial;
  private String email;
  private String cnpj;
  private Boolean ativo;
  @JsonIgnore
  private Set<IgrejaUsuario> usuarios;
  private Endereco endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;
  private LocalDateTime atualizadoEm;
  private LocalDateTime registradoEm;

  public Igreja(IgrejaRequest request) {
    setRazaoSocial(request.getRazaoSocial());
    this.nomeFantasia = request.getNomeFantasia();
    this.email = request.getEmail();
    this.cnpj = request.getCnpj();
    this.endereco = request.getEndereco();
    this.ativo =false;
    this.telefone = request.getTelefone();
    this.redesSociais = request.getRedesSociais();
  }

  public Igreja() {
  }

  public void setRazaoSocial(String razaoSocial) {

    if (razaoSocial == null || razaoSocial.isEmpty())
      throw new IllegalArgumentException("Razão Social inválido");
    this.razaoSocial = razaoSocial;
  }

}
