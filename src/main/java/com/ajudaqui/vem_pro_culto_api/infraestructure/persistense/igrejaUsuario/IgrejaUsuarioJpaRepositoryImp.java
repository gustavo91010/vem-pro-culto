package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuarioRepository;

public class IgrejaUsuarioJpaRepositoryImp implements IgrejaUsuarioRepository {
  private IgrejaUsuarioJpaRepository repository;
  private IgrejaUsuarioMapper mapper;

  public IgrejaUsuario save(IgrejaUsuario model) {

    var igrejaUsuario = repository.save(mapper.toEntity(model));

    return mapper.toModel(igrejaUsuario);
  }

}
