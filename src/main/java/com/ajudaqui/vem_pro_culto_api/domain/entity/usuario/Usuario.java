package com.ajudaqui.vem_pro_culto_api.domain.entity.usuario;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.Set;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.UsuarioDTO;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Usuario {

  private Long id;
  private Boolean ativo;
  private UUID authToken;
  private LocalDateTime atualizadoEm;
  private LocalDateTime registradoEm;
  private Endereco endereco;
  @JsonIgnore
  private Set<IgrejaUsuario> igrejas;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

  public Usuario(UsuarioDTO dto) {

    setAuthToken(dto.getAuthToken());
    this.ativo = true;
  }

  public Usuario(String authToken) {

    setAuthToken(authToken);
    this.ativo = true;
  }

  public void setAuthToken(String authToken) {

    if (authToken == null || authToken.isBlank())
      throw new IllegalArgumentException("AuthToken é obrigatório");

    this.authToken = UUID.fromString(authToken);
  }
}
