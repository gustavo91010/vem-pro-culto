package com.ajudaqui.vem_pro_culto_api.application.service.dto;

import java.time.LocalDateTime;

public interface AtividadeView {
    Long getId();
    Long getIgrejaId();
    String getNomeIgreja();
    String getDescricao();
    String getTipo(); 
    LocalDateTime getHorario();
}

