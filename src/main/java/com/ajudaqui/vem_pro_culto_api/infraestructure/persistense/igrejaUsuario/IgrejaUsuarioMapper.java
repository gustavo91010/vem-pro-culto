package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja.IgrejaMapper;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario.UsuarioMapper;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IgrejaUsuarioMapper {
  private final IgrejaMapper igrejaMapper;
  private final UsuarioMapper usuarioMapper;

  public IgrejaUsuarioEntity toEntity(IgrejaUsuario model) {

    return new IgrejaUsuarioEntity(
        model.getId(),
        igrejaMapper.toEntity(model.getIgreja()),
        usuarioMapper.toEntity(model.getUsuario()),
        model.getPapel());
  }

  public IgrejaUsuario toModel(IgrejaUsuarioEntity entity) {

    return new IgrejaUsuario(
        entity.getId(),
        igrejaMapper.toModel(entity.getIgreja()),
        usuarioMapper.toModel(entity.getUsuario()),
        entity.getPapel());
  }

}
