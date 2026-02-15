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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "igreja")
public class Igreja {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "nome_fantasia", nullable = false, length = 100)
  private String nomeFantasia;

  @Column(name = "rezao_social", nullable = true, unique = true, length = 100)
  private String razaoSocial;

  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @Column(name = "senha", nullable = false, length = 50)
  private String senha;

  @OneToMany
  @JoinTable(name = "igreja_telefone", joinColumns = @JoinColumn(name = "igreja_id"), inverseJoinColumns = @JoinColumn(name = "telefone_id"))
  private List<Telefone> telefone;

  @OneToMany
  @JoinTable(name = "igreja_rede_social", joinColumns = @JoinColumn(name = "igreja_id"), inverseJoinColumns = @JoinColumn(name = "rede_social_id"))
  private List<RedeSocial> redesSociais;

  @Column(name = "cnpj", nullable = true, length = 14)
  private String cnpj;

  @ManyToOne(optional = false)
  @JoinColumn(name = "endereco_id", nullable = false)
  private Endereco endereco;

  @UpdateTimestamp
  @Column(name = "atualizado_em", nullable = false)
  private LocalDateTime atualizadoEm;

  @CreationTimestamp
  @Column(name = "registrado_em", nullable = false, updatable = false)
  private LocalDateTime registradoEm;

}
