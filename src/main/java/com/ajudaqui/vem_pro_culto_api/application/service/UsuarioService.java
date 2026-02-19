package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

public interface UsuarioService {

  public UsuarioResponse registro(UsuarioRequest dto);

  List<UsuarioResponse> buscarTodos();

  public Usuario findById(Long usuarioId);

  public UsuarioResponse atualizar(Long usuarioId, UsuarioUpdate usuario);

  public boolean desatvarConta(Long usuarioId);

  Usuario findByEmail(String email);

}
