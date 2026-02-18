package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

public interface UsuarioService {

  public Usuario registro(UsuarioRequest dto);

  List<Usuario> buscarTodos();

  public UsuarioResponse atualizar(Usuario usuario);

  public boolean desatvarConta(Long usuarioId);

}
