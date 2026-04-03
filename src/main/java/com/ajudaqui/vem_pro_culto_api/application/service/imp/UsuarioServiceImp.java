package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.web.config.JwtUtils;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.dto.RelacaoComIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuarioRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.UsuarioRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {

  private final IgrejaUsuarioRepository igrejaUsuarioRepository;
  private final UsuarioRepository usuarioRepository;
  private final JwtUtils jwtUtils;

  @Override
  public UsuarioResponse registro(UsuarioRequest request) {

    UUID authToken = fromUUID(request.getAuthToken());

    if (usuarioRepository.isRegistered(authToken))
      throw new IllegalArgumentException("Usuario já registrado");

    Usuario usuario = Usuario.builder()
        .authToken(authToken)
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
  public Usuario findById(Long usuarioId) {
    return usuarioRepository.findById(usuarioId)
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
  }

  @Override
  public Usuario findByAuthToken(String authToken) {
    Usuario usuario = usuarioRepository.findByAuthToken(fromUUID(authToken))
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
    return usuario;
  }

  @Override
  public StatusResponse alternarStatus(String authToken) {

    Usuario usuario = getByToken(authToken);
    boolean newStatus = !usuario.getAtivo();
    usuario.setAtivo(newStatus);
    usuarioRepository.save(usuario);
    return new StatusResponse(newStatus, "Mudança de status realizda com sucesso.");
  }

  @Override
  public UsuarioResponse update(String authToken, UsuarioUpdate usuario) {
    Usuario user = getByToken(authToken);
    // user.setTelefone(usuario.getTelefone());
    // user.setEndereco(usuario.getEndereco());
    // user.setRedesSociais(usuario.getRedesSociais());

    return new UsuarioResponse(usuarioRepository.update(user.getId(), user));
  }

  private Usuario getByToken(String authToken) {
    return usuarioRepository.findByAuthToken(fromUUID(authToken))
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
  }

  private UUID fromUUID(String text) {
    if (text == null)
      return null;
    if (text.startsWith("Bearer ")) {
      text = text.substring(7);
    }

    // Se contiver pontos, provavelmente é um JWT da Auth
    if (text.contains(".")) {
      try {
        text = jwtUtils.getAccessToken(text);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    try {
      return UUID.fromString(text);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("AuthToken inválido: '" + text + "'. O sistema espera um UUID ou JWT válido.");
    }
  }

  @Override
  public List<RelacaoComIgrejaDTO> relacaoIgreja(String authToken) {
    return igrejaUsuarioRepository.relacaoComIgrejas(fromUUID(authToken));
  }
}
