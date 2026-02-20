package com.ajudaqui.vem_pro_culto_api.domain.entity.usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

  public List<Usuario> buscarTodos();

  public Usuario save(Usuario usuario);

  public Usuario update(Long usuarioId, Usuario usuario);

  public Optional<Usuario> findById(Long usuarioId);

  public Optional<Usuario> findByEmail(String email);

  public Optional<Usuario> findByAuthToken(UUID uuid);
}
