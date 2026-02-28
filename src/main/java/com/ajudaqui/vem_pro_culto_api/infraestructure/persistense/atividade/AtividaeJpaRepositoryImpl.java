package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.AtividadeRepository;

public class AtividaeJpaRepositoryImpl implements AtividadeRepository {
  private AtividaeSpringDataRepository repository;

  @Override
  public Atividade save(Atividade model) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public List<Atividade> buscarAtividades(Long igrejaId, LocalDate localDate, LocalDate localDate2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarAtividades'");
  }

  @Override
  public void delete(Long atividadeId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Atividade buscarPorId(Long atividadeId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
  }

  @Override
  public Optional<Atividade> findById(Long atividadeId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

}
