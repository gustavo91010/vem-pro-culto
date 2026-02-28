package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.application.exception.UnauthorizedException;
import com.ajudaqui.vem_pro_culto_api.application.service.AtividadeService;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeDTO;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.EPapel;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.AtividadeRepository;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtividadeServiceImp implements AtividadeService {
  private final AtividadeRepository repository;
  private final UsuarioService usuarioService;

  @Override
  public Atividade registro(String authToken, AtividadeDTO dto) {
    var usuario = usuarioService.findByAuthToken(authToken);

    if (!isOwner(usuario.getIgrejas(), dto.getIgrejaId()))
      throw new UnauthorizedException("solicitação não permitida");

    return save(dto.toModel());
  }

  @Override
  public Atividade buscarPorId(Long atividadeId) {

    return repository.findById(atividadeId)
        .orElseThrow(() -> new RuntimeException("Atividade não localizado."));
  }

  @Override
  public List<Atividade> buscarAtividades(Long igrejaId, String dataInicio, String dataFim) {
    if (dataFim == null || dataFim.isBlank())
      dataFim = dataInicio;

    // TODO validar o formato da data no contrller
    List<Atividade> buscarAtividades = repository.buscarAtividades(igrejaId, LocalDate.parse(dataInicio),
        LocalDate.parse(dataFim));
    if (buscarAtividades.isEmpty())
      throw new NotFoundException("Atividade não localizada.");
    return buscarAtividades;
  }

  @Override
  public void excluir(String authToken, Long igrejaId, Long atividadeId) {

    var usuario = usuarioService.findByAuthToken(authToken);
    if (!isOwner(usuario.getIgrejas(), igrejaId))
      throw new UnauthorizedException("solicitação não permitida");

    Atividade atividade = buscarPorId(atividadeId);

    if (atividade.getIgrejaId() != igrejaId)
      throw new UnauthorizedException("solicitação não permitida");

    repository.delete(atividadeId);
  }

  private Atividade save(Atividade model) {
    return repository.save(model);
  }

  private boolean isOwner(Set<IgrejaUsuario> igrejas, Long igrejaId) {
    return igrejas.stream()
        .anyMatch(i -> i.getId().equals(igrejaId)
            && i.getPapel().equals(EPapel.DONO));
  }

}
