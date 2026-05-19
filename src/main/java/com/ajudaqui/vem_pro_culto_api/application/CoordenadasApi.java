package com.ajudaqui.vem_pro_culto_api.application;

import java.math.BigDecimal;

import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;

public interface CoordenadasApi {

  public CoordenadaDTO buscarCordenadas(String cep);
  public String buscarCep(BigDecimal latitude, BigDecimal longitude);
  
}
