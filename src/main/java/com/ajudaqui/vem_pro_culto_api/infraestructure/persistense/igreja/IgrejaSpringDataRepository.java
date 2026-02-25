package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface IgrejaSpringDataRepository
    extends JpaRepository<IgrejaEntity, Long>, JpaSpecificationExecutor<IgrejaEntity> {

  Optional<IgrejaEntity> findByEmail(String email);

  Optional<IgrejaEntity> findByRazaoSocial(String razaoSocial);

  Optional<IgrejaEntity> findByNomeFantasia(String nomeFantasia);

}
