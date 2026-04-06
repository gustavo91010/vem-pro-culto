package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import static com.ajudaqui.vem_pro_culto_api.domain.enums.EPapel.FAVORITO;

import java.util.*;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.application.exception.UnauthorizedException;
import com.ajudaqui.vem_pro_culto_api.application.service.IgrejaService;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.IgrejaRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuarioRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EPapel;

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
  public List<Igreja> listarIgrejasDoUsuario(String authToken, boolean isModerador) {

    if (isModerador)
      return repository.buscarTodas();
    return repository.listarIgrejasDoUsuario(UUID.fromString(authToken));
  }

  @Override
  public List<Igreja> buscarTodas(FiltroBuscaIgrejaDTO dto) {

    List<Igreja> igrejas = repository.buscarTodas(dto);
    return igrejas;
  }

  @Override
  public List<Igreja> buscarPorNomeFantasia(String nomeFantasia) {
    return repository.buscarPorNomeFantasia(nomeFantasia);
  }

  @Override
  public Igreja buscarPorId(Long id, Boolean isActive) {
    return repository.buscarPorIr(id)
        .filter(i -> i.getAtivo().equals(isActive))

        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));

  }

  @Override
  public Igreja atualizarIgreja(String authToken, Long igrejaId, IgrejaUpdate dto) {
    Usuario requested = usuarioService.findByAuthToken(authToken);

    if (!temPermissao(requested.getIgrejas(), igrejaId, EPapel.DONO))
      throw new UnauthorizedException("Solicitação não autorizada");
    Igreja igreja = buscarPorId(igrejaId, true);

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

    if (dto.getDescricao() != null && !dto.getDescricao().isBlank())
      igreja.setDescricao(dto.getDescricao());

    if (dto.getImagemUrl() != null && !dto.getImagemUrl().isBlank())
      igreja.setImagemUrl(dto.getImagemUrl());

    return repository.save(igreja);
  }

  private boolean temPermissao(Set<IgrejaUsuario> usuarios, Long igrejaId, EPapel papel) {
    return usuarios.stream()
        .anyMatch(i -> i.getIgreja().getId().equals(igrejaId)
            && i.getPapel().equals(papel));
  }

  @Override
  public Igreja buscarPorRazaoSocial(String razaoSocial, String jwtToken) {

    // if (jwtToken != null && !jwtToken.isBlank()) {
    // System.out.println("entroi num foi??? "+jwtToken);
    // return repository.listarIgrejasDoUsuario(UUID.fromString(jwtToken)).stream()
    // .filter(i -> razaoSocial.equals(i.getRazaoSocial()))
    // .findFirst()
    // .orElseThrow(() -> new NotFoundException("Igreja não localizada."));

    // }
    return findByRazaoSocial(razaoSocial)
        .filter(Igreja::getAtivo)
        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));
  }

  private Optional<Igreja> findByRazaoSocial(String razaoSocial) {
    return repository.findByRazaoSocial(razaoSocial);
  }

  @Override
  public Igreja buscarPorEmail(String email) {
    return findByEmail(email)

        .filter(Igreja::getAtivo)
        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));
  }

  private Optional<Igreja> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  @Override
  public StatusResponse alternarStatus(String authToken, Long igrejaId) {
    var igreja = repository.buscarPorIr(igrejaId)
        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));

    boolean newStatus = !igreja.getAtivo();
    igreja.setAtivo(newStatus);
    repository.save(igreja);
    return new StatusResponse(newStatus, "Mudança de status realizda com sucesso.");
  }

  @Override
  public Boolean vincularUsuario(String authToken, Long igrejaId) {
    boolean jaExiste = usuarioService.relacaoIgreja(authToken).stream()
        .filter(i -> i.getIgrejaId().equals(igrejaId)
            && i.getPapel().equals(FAVORITO.name()))
        .findFirst()
        .isPresent();

    Usuario usuario = usuarioService.findByAuthToken(authToken);

    if (jaExiste) {
      removerVinculo(usuario.getAuthToken(), igrejaId, FAVORITO.name());
      return false;
    }

    Igreja igreja = repository.buscarPorIr(igrejaId)
        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));
    igrejaUsuarioRepository.save(new IgrejaUsuario(igreja, usuario,
        FAVORITO));

    return true;
  }

  private int removerVinculo(UUID authToken, Long igrejaId, String papel) {
    return igrejaUsuarioRepository.removerVinculo(authToken, igrejaId, papel);
  }

}
