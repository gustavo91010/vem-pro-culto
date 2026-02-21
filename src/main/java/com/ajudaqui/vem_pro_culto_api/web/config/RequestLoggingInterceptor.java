package com.ajudaqui.vem_pro_culto_api.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (handler instanceof HandlerMethod) {

      String authHeader = "";

      if (request.getHeader("Authorization") != null) {
        authHeader = "| token: " + request.getHeader("Authorization");
      }

      HandlerMethod method = (HandlerMethod) handler;
      String controller = method.getBeanType().getSimpleName();
      String methodName = method.getMethod().getName();
      String path = request.getRequestURI();
      String httpMethod = request.getMethod();
      String ip = request.getRemoteAddr();
      log.info("{} | {} | {} : [{}] {} {}", ip, controller, methodName, httpMethod, path, authHeader);
    }

    return true;
  }

  
}
