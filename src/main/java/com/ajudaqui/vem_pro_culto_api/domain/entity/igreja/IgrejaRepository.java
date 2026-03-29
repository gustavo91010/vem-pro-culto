package com.ajudaqui.vem_pro_culto_api.domain.entity.igreja;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;

public interface IgrejaRepository {

    Optional<Igreja> findByEmail(String email);
    Optional<Igreja> findByRazaoSocial(String razaoSocial);
    Igreja save(Igreja model);
    List<Igreja> buscarTodas(FiltroBuscaIgrejaDTO dto );
    List<Igreja> buscarTodas();
    List<Igreja> buscarPorNomeFantasia(String nomeFantasia);
    Optional<Igreja> buscarPorIr(Long id);
    List<Igreja> listarIgrejasDoUsuario(UUID fromString);

}
