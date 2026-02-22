package com.ajudaqui.vem_pro_culto_api.domain.entity.igreja;

import java.util.Optional;

public interface IgrejaRepository {

    Optional<Igreja> findByEmail(String email);

    Optional<Igreja> findByRazaoSocial(String razaoSocial);

    Igreja save(Igreja model);

  
}
