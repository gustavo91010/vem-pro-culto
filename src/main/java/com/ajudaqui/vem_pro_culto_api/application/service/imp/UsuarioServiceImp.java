package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
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
  public boolean desatvarConta(Long usuarioId) {

    var usuario = usuarioRepository.findById(usuarioId);
    usuario.setAtualizadoEm(LocalDateTime.now()); // Será que preciso disso mesmo?
    usuario.setAtivo(false);
    return !usuarioRepository.save(usuario).getAtivo();
  }

  @Override
  public UsuarioResponse registro(UsuarioRequest request) {
    if (findByEmail(request.getEmail()) != null) {
      throw new IllegalArgumentException("Email já registrado");
    }

    Usuario usuario = Usuario.builder()
        .nome(request.getNome())
        .email(request.getEmail())
        .senha(request.getSenha())
        .authToken(UUID.fromString(request.getAuthToken()))
        .ativo(true)
        .telefone(request.getTelefone())
        .endereco(request.getEndereco())
        .redesSociais(request.getRedesSociais())
        .build();

    return new UsuarioResponse(usuarioRepository.save(usuario));
  }

  @Override
  public List<UsuarioResponse> buscarTodos() {
    return usuarioRepository.buscarTodos().stream()
        .map(UsuarioResponse::new)
        .toList();
  }

  @Override
  public Usuario findByEmail(String email) {
    return usuarioRepository.findByEmail(email);
  }

  @Override
  public Usuario findById(Long usuarioId) {
    return usuarioRepository.findById(usuarioId);
  }

  @Override
  public UsuarioResponse atualizar(Long usuarioId, UsuarioUpdate usuario) {
    Usuario user = findById(usuarioId);
    user.setNome(usuario.getNome());
    user.setEmail(usuario.getEmail());
    user.setTelefone(usuario.getTelefone());
    user.setEndereco(usuario.getEndereco());
    user.setRedesSociais(usuario.getRedesSociais());
    user.setAtualizadoEm(LocalDateTime.now());
    // Será queprecisa mesmo ou aquela anotação resolve??

    return new UsuarioResponse(usuarioRepository.save(user));
  }

}
