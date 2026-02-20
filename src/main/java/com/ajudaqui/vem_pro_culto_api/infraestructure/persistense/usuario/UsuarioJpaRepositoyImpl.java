package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.*;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioJpaRepositoyImpl implements UsuarioRepository {

  private final UsuarioJpaRepository repository;
  private final UsuarioMapper mapper;

  @Override
  public List<Usuario> buscarTodos() {
    return repository.findAll().stream()
        .map(mapper::toModel)
        .toList();
  }

  @Override
  public Usuario save(Usuario usuario) {
    UsuarioEntity entity = repository.save(mapper.toEntity(usuario));

    return mapper.toModel(entity);
  }

  @Override
  public Optional<Usuario> findById(Long usuarioId) {
    return repository.findById(usuarioId)
        .map(mapper::toModel);

  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    return repository.findByEmail(email)
        .map(mapper::toModel);
  }

  @Override
  public Optional<Usuario> findByAuthToken(UUID authToken) {
    return repository.findByAuthToken(authToken)
        .map(mapper::toModel);
  }

  @Override
  public Usuario update(Long usuarioId, Usuario usuario) {
    UsuarioEntity user = repository.findById(usuarioId)
        .orElseThrow(() -> new RuntimeException("Usuário não localizado."));
    user.setNome(usuario.getNome());
    user.setEmail(usuario.getEmail());
    user.setAtivo(usuario.getAtivo());
    user.setAuthToken(usuario.getAuthToken());
    // user.setTelefone(usuario.getTelefone());
    // user.setEndereco(usuario.getEndereco());
    // user.setRedesSociais(usuario.getRedesSociais());
    user.setAtualizadoEm(LocalDateTime.now());
    repository.save(user);

    return mapper.toModel(user);
  }

}
