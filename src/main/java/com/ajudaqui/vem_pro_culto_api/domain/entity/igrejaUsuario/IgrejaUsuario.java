package com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Papel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IgrejaUsuario {

  private Long id;
  private Igreja igreja;

  private Usuario usuario;

  private Papel papel;
}
