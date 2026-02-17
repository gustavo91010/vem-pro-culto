package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.time.LocalDateTime;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity {

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

  @ElementCollection
  @CollectionTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "usuario_id"))
  private List<Endereco> endereco;

  @ElementCollection
  @CollectionTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "usuario_id"))
  private List<Telefone> telefone;

  @ElementCollection
  @CollectionTable(name = "usuario_rede_social", joinColumns = @JoinColumn(name = "usuario_id"))
  private List<RedeSocial> redesSociais;
}
