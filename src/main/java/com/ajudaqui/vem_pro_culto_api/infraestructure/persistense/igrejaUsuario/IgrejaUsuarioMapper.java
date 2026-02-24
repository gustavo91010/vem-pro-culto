package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja.IgrejaEntity;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario.UsuarioEntity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IgrejaUsuarioMapper {

  public IgrejaUsuarioEntity toEntity(IgrejaUsuario model) {

    return new IgrejaUsuarioEntity(
        model.getId(),
        IgrejaEntity.builder().id(model.getIgreja().getId()).build(),
        UsuarioEntity.builder().id(model.getUsuario().getId()).build(),
        model.getPapel());
  }

  public IgrejaUsuario toModel(IgrejaUsuarioEntity entity) {
    return new IgrejaUsuario(
        entity.getId(),
        Igreja.builder().id(entity.getIgreja().getId()).build(),
        Usuario.builder().id(entity.getUsuario().getId()).build(),
        entity.getPapel());
  }

}
