package com.ajudaqui.vem_pro_culto_api.domain.entity.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;

public interface UsuarioRepository {

  public List<Usuario> buscarTodos();
  public Usuario save(Usuario usuario);
  public Usuario update(String authToken, UsuarioUpdate usuario);
  public Optional<Usuario> findById(Long usuarioId);
  public Optional<Usuario> findByAuthToken(UUID authToken);
  Boolean isRegistered(UUID authToken);
}
