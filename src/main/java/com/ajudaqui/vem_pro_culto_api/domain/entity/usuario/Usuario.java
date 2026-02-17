package com.ajudaqui.vem_pro_culto_api.domain.entity.usuario;

import java.time.LocalDateTime;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.UsuarioDTO;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.*;

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
  private LocalDateTime atualizadoEm;
  private LocalDateTime registradoEm;
  private List<Endereco> endereco;
  private List<Telefone> telefone;
  private List<RedeSocial> redesSociais;

  public Usuario(UsuarioDTO dto) {

    setNome(dto.getNome());
    setEmail(dto.getEmail());
    this.senha = dto.getSenha();
    this.atualizadoEm = LocalDateTime.now();
    this.registradoEm = LocalDateTime.now();
  }

  public Usuario(String nome, String email, String senha) {

    setNome(nome);
    setEmail(email);
    this.senha = senha;
    this.atualizadoEm = LocalDateTime.now();
    this.registradoEm = LocalDateTime.now();
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
}
