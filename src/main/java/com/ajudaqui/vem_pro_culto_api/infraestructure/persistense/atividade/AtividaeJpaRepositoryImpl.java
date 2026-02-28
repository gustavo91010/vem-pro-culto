package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.AtividadeRepository;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AtividaeJpaRepositoryImpl implements AtividadeRepository {
  private final AtividaeSpringDataRepository repository;
  private final AtividadeMapper mapper;

  @Override
  public Atividade save(Atividade model) {
    AtividadeEntity entity = repository.save(mapper.toEntity(model));
    return mapper.toModel(entity);
  }

  @Override
  public List<Atividade> buscarAtividades(Long igrejaId, LocalDate dataInicio, LocalDate dataFim) {
    return repository.buscarAtividades(igrejaId, dataInicio, dataFim).stream()
        .map(mapper::toModel).toList();
  }

  @Override
  public void delete(Long atividadeId) {
    repository.deleteById(atividadeId);
  }

  @Override
  public Optional<Atividade> findById(Long atividadeId) {
    return repository.findById(atividadeId)
        .map(mapper::toModel);
  }

}
