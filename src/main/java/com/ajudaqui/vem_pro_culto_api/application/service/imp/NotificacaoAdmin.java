package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Telefone;

public record NotificacaoAdmin(
    UUID authToken,
    List<Telefone> usuarioTelefone,
    String emailIgreja,
    String nomeFantasia,
    List<Telefone> igrejaTelefone) {


}
