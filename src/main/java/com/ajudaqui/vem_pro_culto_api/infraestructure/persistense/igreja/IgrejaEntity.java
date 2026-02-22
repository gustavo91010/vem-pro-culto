package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.endereco.EnderecoComp;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.redeSocial.RedeSocialComp;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.telefone.TelefoneComp;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario.UsuarioEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "igreja")
public class IgrejaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "nome_fantasia", nullable = true, length = 100)
  private String nomeFantasia;

  @Column(name = "rezao_social", nullable = false, unique = true, length = 100)
  private String razaoSocial;

  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;

  @OneToMany(mappedBy = "igreja")
  private Set<IgrejaUsuario> usuarios; // @ManyToOne
  // @JoinColumn(name = "usuario_id")
  // private UsuarioEntity usuario;

  @Embedded
  private EnderecoComp endereco;

  @Column(name = "ativo")
  private Boolean ativo;

  @Column(name = "cnpj", nullable = true, length = 14, unique = true)
  private String cnpj;

  @UpdateTimestamp
  @Column(name = "atualizado_em", nullable = false)
  private LocalDateTime atualizadoEm;

  @CreationTimestamp
  @Column(name = "registrado_em", nullable = false, updatable = false)
  private LocalDateTime registradoEm;

  @ElementCollection
  @CollectionTable(name = "igreja_telefone", joinColumns = @JoinColumn(name = "igreja_id"))
  private List<TelefoneComp> telefone;

  @ElementCollection
  @CollectionTable(name = "igreja_rede_social", joinColumns = @JoinColumn(name = "igreja_id"))
  private List<RedeSocialComp> redesSociais;

}
