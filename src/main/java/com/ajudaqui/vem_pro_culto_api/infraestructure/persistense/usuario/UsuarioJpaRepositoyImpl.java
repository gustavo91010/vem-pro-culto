package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.*;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioJpaRepositoyImpl implements UsuarioRepository {

  private final UsuarioJpaRepository repository;
  private final UsuarioMapper mapper;

  @Override
  public Usuario registro(Usuario usuario) {
    UsuarioEntity entity = repository.save(mapper.toEntity(usuario));

    return mapper.toModel(entity);
  }

  @Override
  public List<Usuario> buscarTodos() {
    return repository.findAll().stream()
        .map(mapper::toModel)
        .toList();
  }

}
