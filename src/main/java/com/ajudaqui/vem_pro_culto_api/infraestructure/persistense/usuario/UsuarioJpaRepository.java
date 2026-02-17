package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity,Long> {

}
