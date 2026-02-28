package com.ajudaqui.vem_pro_culto_api.domain.entity.atividade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AtividadeRepository {

    Atividade save(Atividade model);
    List<Atividade> buscarAtividades(Long igrejaId, LocalDate localDate, LocalDate localDate2);
    void delete(Long atividadeId);
    Optional<Atividade> findById(Long atividadeId);

  
}
