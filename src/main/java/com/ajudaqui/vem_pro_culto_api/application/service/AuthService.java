package com.ajudaqui.vem_pro_culto_api.application.service;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;

public interface AuthService {

    UsuarioResponse register(String authApp, UsuarioRequest dto);

}
