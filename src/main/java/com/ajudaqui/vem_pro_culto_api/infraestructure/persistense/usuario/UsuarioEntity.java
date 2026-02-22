package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.endereco.EnderecoComp;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.redeSocial.RedeSocialComp;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.telefone.TelefoneComp;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja.IgrejaEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

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

  @Column(name = "email", nullable = false, length = 100, unique = true)
  private String email;

  @Column(name = "senha", nullable = false, length = 50)
  private String senha;

  @Column(name = "ativo")
  private Boolean ativo;

  @Column(name = "auth_token", nullable = false)
  private UUID authToken;

  @OneToMany(mappedBy = "igreja")
  private Set<IgrejaUsuario> usuarios;
  // @OneToMany(mappedBy = "usuario")
  // private List<IgrejaEntity> igrejas;

  @UpdateTimestamp
  @Column(name = "atualizado_em", nullable = false)
  private LocalDateTime atualizadoEm;

  @CreationTimestamp
  @Column(name = "registrado_em", nullable = false, updatable = false)
  private LocalDateTime registradoEm;

  @Embedded
  private EnderecoComp endereco;

  @ElementCollection
  @CollectionTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "usuario_id"))
  private List<TelefoneComp> telefone;

  @ElementCollection
  @CollectionTable(name = "usuario_rede_social", joinColumns = @JoinColumn(name = "usuario_id"))
  private List<RedeSocialComp> redesSociais;
}
