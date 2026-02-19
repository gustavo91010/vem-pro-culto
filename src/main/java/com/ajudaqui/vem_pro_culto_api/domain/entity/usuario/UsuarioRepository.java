package com.ajudaqui.vem_pro_culto_api.domain.entity.usuario;

import java.util.List;

public interface UsuarioRepository {


  public List<Usuario> buscarTodos();

  public Usuario save(Usuario usuario);

  public Usuario findById(Long usuarioId);

  public Usuario findByEmail(String email);
}
