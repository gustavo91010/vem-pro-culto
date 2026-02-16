package com.ajudaqui.vem_pro_culto_api.application.service;

import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.Usuario;

public interface UsuarioService {

  public Usuario registro(Usuario usuario);

  public UsuarioResponse atualizar(Usuario usuario);

  public boolean desatvarConta(Long usuarioId);

}
