package com.ajudaqui.vem_pro_culto_api.domain.entity.igreja;

import java.util.List;
import java.util.Optional;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;

import org.springframework.data.jpa.domain.Specification;

public interface IgrejaRepository {

    Optional<Igreja> findByEmail(String email);

    Optional<Igreja> findByRazaoSocial(String razaoSocial);

    Igreja save(Igreja model);

    List<Igreja> buscarTodas(FiltroBuscaIgrejaDTO dto );

    List<Igreja> buscarPorNomeFantasia(String nomeFantasia);

    Optional<Igreja> buscarPorIr(Long id);

}
