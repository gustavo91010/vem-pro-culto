package com.ajudaqui.vem_pro_culto_api.domain.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CoordenadaDTO {

  private String cep;
  private BigDecimal latitude;

  private BigDecimal longitude;
}
