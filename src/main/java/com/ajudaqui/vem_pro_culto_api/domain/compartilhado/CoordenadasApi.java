package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;

public interface CoordenadasApi {

  public CoordenadaDTO buscarCordenadas(String cep);
  public String buscarCep(String latitude, String longitude);
  
}
