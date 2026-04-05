package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.dto.RelacaoComIgrejaDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface IgrejaUsuarioJpaRepository extends JpaRepository<IgrejaUsuarioEntity, Long> {

  @Query(value = """
      SELECT igreja_id as igrejaId, papel
      FROM igreja_usuario
      WHERE usuario_id=(
        SELECT usuario
        FROM usuario
        WHERE auth_token= :authToken
        );
      """, nativeQuery = true)
  public List<RelacaoComIgrejaDTO> relacaoComIgrejas(@Param("authToken") UUID authToken);

  @Modifying
  @Transactional
  @Query(value = """
      DELETE FROM igreja_usuario
      WHERE igreja_id = :igrejaId
      AND usuario_id = :usuarioId
      AND papel = :papel
        """, nativeQuery = true)
  public int removerVinculo(Long usuarioId, Long igrejaId, String papel);
}
