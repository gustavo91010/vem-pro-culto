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
  private String nome;
  private String email;
  private String senha;
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

    setNome(dto.getNome());
    setEmail(dto.getEmail());
    setAuthToken(dto.getAuthToken());
    this.senha = dto.getSenha();
    this.ativo = true;
    // this.atualizadoEm = LocalDateTime.now();
    // this.registradoEm = LocalDateTime.now();
  }

  public Usuario(String nome, String email, String senha, String authToken) {

    setNome(nome);
    setEmail(email);
    setAuthToken(authToken);
    this.senha = senha;
    this.ativo = true;
    // this.atualizadoEm = LocalDateTime.now();
    // this.registradoEm = LocalDateTime.now();
  }

  public void setEmail(String email) {
    if (email == null || !email.contains("@"))
      throw new IllegalArgumentException("Email inválido");

    this.email = email;
  }

  public void setNome(String nome) {
    if (nome == null || nome.isBlank())
      throw new IllegalArgumentException("Nome obrigatório");

    this.nome = nome;
  }

  public void setAuthToken(String authToken){

    if (authToken == null || authToken.isBlank())
      throw new IllegalArgumentException("AuthToken é obrigatório");

    this.authToken = UUID.fromString(authToken);
  }
}
