package com.ajudaqui.vem_pro_culto_api.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "usuario", nullable = false, updatable = false)
  private Long id;

  @Column(name = "nome", nullable = false, length = 100)
  private String nome;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "senha", nullable = false, length = 50)
  private String senha;

  @UpdateTimestamp
  @Column(name = "atualizado_em", nullable = false)
  private LocalDateTime atualizadoEm;

  @CreationTimestamp
  @Column(name = "registrado_em", nullable = false, updatable = false)
  private LocalDateTime registradoEm;

  @OneToMany
  @JoinTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "telefone_id"))
  private List<Telefone> telefone;

  @OneToMany
  @JoinTable(name = "usuario_rede_social", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rede_social_id"))
  private List<RedeSocial> redesSociais;
}
