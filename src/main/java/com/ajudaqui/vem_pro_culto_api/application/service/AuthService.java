package com.ajudaqui.vem_pro_culto_api.application.service;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;

public interface AuthService {

    void register(String authApp, UsuarioRequest dto);

}
