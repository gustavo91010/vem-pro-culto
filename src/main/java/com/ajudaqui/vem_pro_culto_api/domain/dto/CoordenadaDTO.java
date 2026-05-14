package com.ajudaqui.vem_pro_culto_api.domain.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CoordenadaDTO {

  @JsonProperty("name")
  private String cep;

  @JsonProperty("address")
  private Address endereco;

  @JsonProperty("postcode")
  private String postcode;

  @JsonProperty("lat")
  private BigDecimal latitude;

  @JsonProperty("lon")
  private BigDecimal longitude;

  @Data
  public static class Address {
    @JsonProperty("postcode")
    private String cep;

  }
}
