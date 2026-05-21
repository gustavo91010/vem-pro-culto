package com.ajudaqui.vem_pro_culto_api.application.service;

import java.math.BigDecimal;

import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;

public interface CoordenadasApi {

  public CoordenadaDTO buscarCordenadas(String cep, String rua, String estado);
  public String buscarCep(BigDecimal latitude, BigDecimal longitude);
  
}
