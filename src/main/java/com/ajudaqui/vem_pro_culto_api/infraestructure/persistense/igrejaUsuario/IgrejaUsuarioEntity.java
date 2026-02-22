package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Papel;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja.IgrejaEntity;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario.UsuarioEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "igreja_usuario")
public class IgrejaUsuarioEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "igreja_usuario_id", nullable = false, updatable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "igreja_id", nullable = false)
  private IgrejaEntity igreja;

  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;

  @Enumerated(EnumType.STRING)
  private Papel papel;
}
