package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import java.time.LocalDate;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeView;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividaeSpringDataRepository extends JpaRepository<AtividadeEntity, Long> {
  @Query(value = """
      SELECT *
      FROM atividades
      WHERE igreja_id = :igrejaId
        AND data BETWEEN :dataInicio AND :dataFim
      """, nativeQuery = true)
  List<AtividadeEntity> buscarAtividades(
      @Param("igrejaId") Long igrejaId,
      @Param("dataInicio") LocalDate dataInicio,
      @Param("dataFim") LocalDate dataFim);

  @Query(value = """
          SELECT
              a.id,
              a.igreja_id AS igrejaId,
              i.nome_fantasia AS nomeIgreja,
              a.descricao,
              a.tipo,
              a.horario
          FROM atividade a
          JOIN igreja i ON a.igreja_id = i.id
          WHERE i.ativo = true
      """, nativeQuery = true)
  List<AtividadeView> buscarAtividadesComIgrejaAtiva();
}
