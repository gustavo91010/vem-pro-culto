package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Papel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "igreja")
public class IgrejaUsuarioEntity {

  @ManyToOne
  @MapsId("igrejaId")
  private Igreja igreja;

  @ManyToOne
  @MapsId("usuarioId")
  private Usuario usuario;

  @Enumerated(EnumType.STRING)
  private Papel papel;
}
