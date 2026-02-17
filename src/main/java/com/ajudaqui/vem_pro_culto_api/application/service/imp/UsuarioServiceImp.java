package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.UsuarioDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.UsuarioRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {
  private final UsuarioRepository usuarioRepository;

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

  @Override
  public Usuario registro(UsuarioDTO dto) {
    return usuarioRepository.registro(new Usuario(dto));
  }

  @Override
  public List<Usuario> buscarTodos() {
    return usuarioRepository.buscarTodos();
  }

}
