package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.RedeSocial;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
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

  @OneToMany(mappedBy = "igreja", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<IgrejaUsuarioEntity> usuarios; // @ManyToOne

  @Embedded
  private Endereco endereco;

  @Column(name = "ativo")
  private Boolean ativo;

  @Column(name = "cnpj", nullable = true, length = 14)
  private String cnpj;

  @UpdateTimestamp
  @Column(name = "atualizado_em", nullable = false)
  private LocalDateTime atualizadoEm;

  @CreationTimestamp
  @Column(name = "registrado_em", nullable = false, updatable = false)
  private LocalDateTime registradoEm;

  @ElementCollection
  @CollectionTable(name = "igreja_telefone", joinColumns = @JoinColumn(name = "igreja_id"))
  private List<Telefone> telefone;

  @ElementCollection
  @CollectionTable(name = "igreja_rede_social", joinColumns = @JoinColumn(name = "igreja_id"))
  private List<RedeSocial> redesSociais;

}
