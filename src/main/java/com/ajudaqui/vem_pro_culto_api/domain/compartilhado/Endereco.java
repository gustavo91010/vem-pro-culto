package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.*;

// @Getter
// @Setter
@Builder
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;
  private String pais;

  private BigDecimal latitude;

  private BigDecimal longitude;
}

