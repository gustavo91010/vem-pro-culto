package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgrejaJpaRepository extends JpaRepository<IgrejaEntity,Long> {

    Optional<IgrejaEntity> findByEmail(String email);

    Optional<IgrejaEntity> findByRazaoSocial(String razaoSocial);

    Optional<IgrejaEntity> buscarPorNomeFantasia(String nomeFantasia);


}
