package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import static com.ajudaqui.vem_pro_culto_api.domain.enums.EPapel.FAVORITO;

import java.util.*;

import com.ajudaqui.vem_pro_culto_api.application.exception.*;
import com.ajudaqui.vem_pro_culto_api.application.service.*;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.*;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;
import com.ajudaqui.vem_pro_culto_api.domain.dto.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.*;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EPapel;
import com.ajudaqui.vem_pro_culto_api.web.config.JwtUtils;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@Log
@RequiredArgsConstructor
public class IgrejaServiceImp implements IgrejaService {
  private final IgrejaRepository repository;
  private final UsuarioService usuarioService;
  private final IgrejaUsuarioRepository igrejaUsuarioRepository;
  private final CoordenadaApiImp coordenadaApi;
  private final EmailServiceImp emailServiceImp;
  private final JwtUtils jwtUtils;
  private final String EMAIL_ADMIN = "contato.vemproculto@gmail.com";

  @Override
  public Igreja registro(String requestedToken, IgrejaRequest igrejaRequest) {

    if (findByRazaoSocial(igrejaRequest.getRazaoSocial()).isPresent())
      throw new IllegalArgumentException("Razão Social já registrado");

    if (findByEmail(igrejaRequest.getEmail()).isPresent())
      throw new IllegalArgumentException("Email já registrado");
    alimentandoCoordenada(igrejaRequest);
    Usuario usuario = usuarioService.findByAuthToken(requestedToken);
    var igreja = repository.save(new Igreja(igrejaRequest));

    var igrejaUsuario = new IgrejaUsuario(igreja, usuario, EPapel.DONO);
    IgrejaUsuario novaIgreja = igrejaUsuarioRepository.save(igrejaUsuario);

    notificandoAdmin(novaIgreja);
    return igreja;
  }

  private void notificandoAdmin(IgrejaUsuario novaIgreja) {
    Usuario usuario = novaIgreja.getUsuario();
    Igreja igreja = novaIgreja.getIgreja();

    NotificacaoAdmin notificacaoAdmin = new NotificacaoAdmin(
        usuario.getAuthToken(),
        usuario.getTelefone(),
        igreja.getEmail(),
        igreja.getNomeFantasia(),
        igreja.getTelefone());

    try {
      emailServiceImp.send(EMAIL_ADMIN,
          "Nova Igreja: " + igreja.getNomeFantasia(), notificacaoAdmin.toString());
    } catch (Exception e) {
      log.warning(String.format("Email para endereço %s não foi enviado.", EMAIL_ADMIN));
    }
  }

  private void alimentandoCoordenada(IgrejaRequest igrejaRequest) {

    if (igrejaRequest.getEndereco() == null)
      throw new BadRequestException("O endereço é obrigatório.");

    Endereco endereco = igrejaRequest.getEndereco();

    String cep = endereco.getCep();
    if (cep == null || cep.isBlank())
      throw new BadRequestException("O CEP deve ser preencido");

    CoordenadaDTO coordenada = coordenadaApi.buscarCordenadas(
        cep, endereco.getLogradouro(), endereco.getEstado());

    if (coordenada == null)
      throw new BadRequestException("Não foi possível localizar coordenadas para o CEP informado.");

    endereco.setLatitude(coordenada.latitude());
    endereco.setLongitude(coordenada.longitude());
    // igrejaRequest.setEndereco(endereco);
  }

  @Override
  public List<Igreja> listarIgrejasDoUsuario(String authToken, boolean isModerador) {

    if (isModerador)
      return repository.buscarTodas();
    return repository.listarIgrejasDoUsuario(UUID.fromString(authToken));
  }

  @Override
  public List<Igreja> buscarTodas(Boolean isAdmin, FiltroBuscaIgrejaDTO dto, Boolean ativo) {

    if (!ativo && !Boolean.TRUE.equals(isAdmin))
      throw new UnauthorizedException("Solicitação não autorizada");

    // TODO depois mudar para o paginado
    // TODO Criar o endpoitn search para fazer os filtros especificos...
    List<Igreja> igrejas = repository.buscarTodas();

    return igrejas.stream()
        .filter(i -> i.getAtivo().equals(ativo))
        .toList();
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

    var igreja = findByRazaoSocial(razaoSocial)
        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));

    if (!igreja.getAtivo()) {

      if (jwtToken == null || jwtToken.isBlank())
        throw new NotFoundException("Igreja não localizada.");

      if (jwtUtils.isAdmin(jwtToken))
        return igreja;

      List<RelacaoComIgrejaDTO> relacoes = igrejaUsuarioRepository
          .relacaoComIgrejas(UUID.fromString(jwtUtils.getAccessToken(jwtToken)));
      boolean isOwner = relacoes.stream()
          .anyMatch(r -> r.getIgrejaId().equals(igreja.getId())
              && r.getPapel().equals(EPapel.DONO.name()));

      if (isOwner)
        return igreja;

      throw new NotFoundException("Igreja não localizada.");
    }
    return igreja;
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
  public StatusResponse alternarStatus(Boolean isAdmin, Long igrejaId) {

    if (!isAdmin)
      throw new UnauthorizedException("Solicitação não autorizada");

    var igreja = repository.buscarPorIr(igrejaId)
        .orElseThrow(() -> new NotFoundException("Igreja não localizada."));

    boolean primeiraAlteracao = igreja.getAtualizadoEm().equals(igreja.getRegistradoEm());
    boolean newStatus = !igreja.getAtivo();

    igreja.setAtivo(newStatus);
    repository.save(igreja);

    if (primeiraAlteracao && newStatus) {

      emailServiceImp.send(
          igreja.getEmail(),
          "Cadastro aprovado",
          String.format(
              "Olá! Seu cadastro foi aprovado e a igreja %s já pode utilizar os serviços da plataforma Vem Pro Culto. Seja bem-vindo!",
              igreja.getRazaoSocial()));
    }
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
