package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

  Optional<UsuarioEntity> findByAuthToken(UUID authToken);

  @Query(value = "SELECT count(*) > 0 FROM usuario WHERE auth_token = :authToken", nativeQuery = true)
  Boolean isRegistered(@Param("authToken") UUID authToken);

}
