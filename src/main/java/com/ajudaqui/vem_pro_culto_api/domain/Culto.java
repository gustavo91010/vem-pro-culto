package com.ajudaqui.vem_pro_culto_api.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "culto")
public class Culto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "tipo", nullable = false, length = 100)
  private String tipo;

  @Column(name = "data_hora", nullable = false)
  private LocalDateTime dataHora;

  @ManyToOne
  @JoinColumn(name = "igreja_id")
  private Igreja igreja;
  
}
