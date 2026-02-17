package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.Usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService{

  @Override
  public Usuario registro(Usuario usuario) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'registro'");
  }

  @Override
  public UsuarioResponse atualizar(Usuario usuario) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
  }

  @Override
  public boolean desatvarConta(Long usuarioId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'desatvarConta'");
  }

  
}
