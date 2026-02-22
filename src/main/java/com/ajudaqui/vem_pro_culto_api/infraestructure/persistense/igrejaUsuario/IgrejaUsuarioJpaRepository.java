package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgrejaUsuarioJpaRepository extends JpaRepository<IgrejaUsuarioEntity,Long> {


}
