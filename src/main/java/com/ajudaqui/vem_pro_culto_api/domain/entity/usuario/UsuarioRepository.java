package com.ajudaqui.vem_pro_culto_api.domain.entity.usuario;

import java.util.List;

public interface UsuarioRepository {

  public Usuario registro(Usuario usuario);

  public List<Usuario> buscarTodos();
}
