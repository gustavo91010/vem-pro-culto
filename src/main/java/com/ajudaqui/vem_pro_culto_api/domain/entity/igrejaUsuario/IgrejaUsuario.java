package com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.EPapel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IgrejaUsuario {

  public static Object getUsuarios;
  private Long id;
  private Igreja igreja;

  private Usuario usuario;

  private EPapel papel;

  public IgrejaUsuario(Igreja igreja, Usuario usuario, EPapel papel) {
    this.igreja = igreja;
    this.usuario = usuario;
    this.papel = papel;
  }

}
