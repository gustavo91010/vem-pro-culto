package com.ajudaqui.vem_pro_culto_api.application.service.imp;

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
  public UsuarioResponse registro(UsuarioRequest request) {

    if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
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
    return usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
  }

  @Override
  public Usuario findById(Long usuarioId) {
    return usuarioRepository.findById(usuarioId)
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
  }


  @Override
  public UsuarioResponse findByAuthToken(String authToken) {
    Usuario usuario = usuarioRepository.findByAuthToken(fromUUID(authToken))
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
    return new UsuarioResponse(usuario);
  }

  @Override
  public boolean alternarStatus(String authToken) {

    Usuario usuario = getByToken(authToken);
    usuario.setAtivo(!usuario.getAtivo());
    return usuarioRepository.update(usuario.getId(), usuario).getAtivo();
  }

  @Override
  public UsuarioResponse update(String authToken, UsuarioUpdate usuario) {
    Usuario user = getByToken(authToken);
    user.setNome(usuario.getNome());
    // user.setTelefone(usuario.getTelefone());
    // user.setEndereco(usuario.getEndereco());
    // user.setRedesSociais(usuario.getRedesSociais());
    // user.setAtualizadoEm(LocalDateTime.now());
    // Será queprecisa mesmo ou aquela anotação resolve??

    return new UsuarioResponse(usuarioRepository.update(user.getId(), user));
  }

  private Usuario getByToken(String authToken) {
    return usuarioRepository.findByAuthToken(fromUUID(authToken))
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
  }

  private UUID fromUUID(String text) {
    try {
      return UUID.fromString(text);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("AuthToken inválido.");
    }
  }
}
