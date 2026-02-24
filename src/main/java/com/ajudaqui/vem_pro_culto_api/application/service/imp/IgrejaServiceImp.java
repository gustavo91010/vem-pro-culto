package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.util.*;

import com.ajudaqui.vem_pro_culto_api.application.exception.*;
import com.ajudaqui.vem_pro_culto_api.application.service.*;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.EPapel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IgrejaServiceImp implements IgrejaService {
  private final IgrejaRepository repository;
  private final UsuarioService usuarioService;
  private final IgrejaUsuarioRepository igrejaUsuarioRepository;

  @Override
  public Igreja registro(String requestedToken, IgrejaRequest igrejaRequest) {

    if (findByRazaoSocial(igrejaRequest.getRazaoSocial()).isPresent())
      throw new IllegalArgumentException("Razão Social já registrado");

    if (findByEmail(igrejaRequest.getEmail()).isPresent())
      throw new IllegalArgumentException("Email já registrado");

    Usuario usuario = usuarioService.findByAuthToken(requestedToken);
    var igreja = repository.save(new Igreja(igrejaRequest));

    var igrejaUsuario = new IgrejaUsuario(igreja, usuario, EPapel.DONO);
    igrejaUsuarioRepository.save(igrejaUsuario);
    return igreja;
  }

  @Override
  public List<Igreja> buscarTodas() {
    return repository.buscarTodas();
  }

  @Override
  public List<Igreja> buscarPorNomeFantasia(String nomeFantasia) {
    return repository.buscarPorNomeFantasia(nomeFantasia);
  }

  @Override
  public Igreja buscarPorId(Long id) {
    return repository.buscarPorIr(id)
        .orElseThrow(() -> new NotFoundException("Usuário não localizado."));

  }

  @Override
  public Igreja atualizarIgreja(String authToken, Long igrejaId, IgrejaUpdate dto) {
    Usuario requested = usuarioService.findByAuthToken(authToken);

    if (!temPermissao(requested.getIgrejas(), igrejaId))
      throw new UnauthorizedException("Solicitação não autorizada");
    Igreja igreja = buscarPorId(igrejaId);

    if (dto.getNomeFantasia() != null && !dto.getNomeFantasia().isBlank())
      igreja.setNomeFantasia(dto.getNomeFantasia());

    if (dto.getEmail() != null && !dto.getEmail().isBlank())
      igreja.setEmail(dto.getEmail());

    if (dto.getCnpj() != null && !dto.getCnpj().isBlank())
      igreja.setCnpj(dto.getCnpj());

    if (dto.getEndereco() != null)
      igreja.setEndereco(dto.getEndereco());

    if (dto.getTelefone() != null && !dto.getTelefone().isEmpty())
      igreja.setTelefone(dto.getTelefone());

    if (dto.getRedesSociais() != null && !dto.getRedesSociais().isEmpty())
      igreja.setRedesSociais(dto.getRedesSociais());

    return repository.save(igreja);
  }

  private boolean temPermissao(Set<IgrejaUsuario> usuarios, Long igrejaId) {
    return usuarios.stream()
        .anyMatch(i -> i.getIgreja().getId().equals(igrejaId));
  }

  @Override
  public Igreja buscarPorRazaoSocial(String razaoSocial) {
    return findByRazaoSocial(razaoSocial)
        .orElseThrow(() -> new NotFoundException("Usuário não localizado."));
  }

  private Optional<Igreja> findByRazaoSocial(String razaoSocial) {
    return repository.findByRazaoSocial(razaoSocial);
  }

  @Override
  public Igreja buscarPorEmail(String email) {
    return findByEmail(email)
        .orElseThrow(() -> new NotFoundException("Usuário não localizado."));
  }

  private Optional<Igreja> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public StatusResponse alternarStatus(String authToken, Long igrejaId) {
    Usuario requested = usuarioService.findByAuthToken(authToken);

    if (!temPermissao(requested.getIgrejas(), igrejaId))
      throw new UnauthorizedException("Solicitação não autorizada");

    var igreja = buscarPorId(igrejaId);
    boolean newStatus = !igreja.getAtivo();
    igreja.setAtivo(newStatus);
    repository.save(igreja);
    return new StatusResponse(newStatus, "Mudança de status realizda com sucesso.");
  }
}
