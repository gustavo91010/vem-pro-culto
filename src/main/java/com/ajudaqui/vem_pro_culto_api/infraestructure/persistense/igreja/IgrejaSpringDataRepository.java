package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface IgrejaSpringDataRepository
    extends JpaRepository<IgrejaEntity, Long>, JpaSpecificationExecutor<IgrejaEntity> {

  Optional<IgrejaEntity> findByEmail(String email);

  Optional<IgrejaEntity> findByRazaoSocial(String razaoSocial);

  Optional<IgrejaEntity> findByNomeFantasia(String nomeFantasia);

  @Query(value = """
      SELECT DISTINCT i.* FROM igreja i
      JOIN igreja_usuario iu ON iu.igreja_id = i.id
      JOIN usuario u ON u.usuario = iu.usuario_id
      WHERE u.auth_token = :authToken
        """, nativeQuery = true)
  List<IgrejaEntity> listarIgrejasDoUsuario(UUID authToken);

}
