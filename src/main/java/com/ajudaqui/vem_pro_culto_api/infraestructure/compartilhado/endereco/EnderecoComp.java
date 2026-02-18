package com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.endereco;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EnderecoComp {

  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;
  private String pais;

  @Column(precision = 10, scale = 8)
  private BigDecimal latitude;

  @Column(precision = 11, scale = 8)
  private BigDecimal longitude;
}
