package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.application.exception.UnauthorizedException;
import com.ajudaqui.vem_pro_culto_api.application.service.IgrejaService;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.EPapel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuarioRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

public class IgrejaServiceImp implements IgrejaService {
  private IgrejaRepository repository;
  private UsuarioService usuarioService;
  private IgrejaUsuarioRepository igrejaUsuarioRepository;

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
    Set<IgrejaUsuario> usuarios = requested.getIgrejas();

    boolean temPermissao = usuarios.stream()
        .anyMatch(i -> i.getIgreja().getId().equals(igrejaId));
    if (!temPermissao)
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
  public StatusResponse alternarStatus(Long igrejaId) {
    var igreja = buscarPorId(igrejaId);
    boolean newStatus = !igreja.getAtivo();
    igreja.setAtivo(newStatus);
    repository.save(igreja);
    return new StatusResponse(newStatus, "Mudança de status realizda com sucesso.");
  }
}
