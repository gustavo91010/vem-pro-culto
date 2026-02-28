package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface AtividaeSpringDataRepository    extends JpaRepository<AtividadeEntity, Long> {

}
