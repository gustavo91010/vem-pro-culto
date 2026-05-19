package com.ajudaqui.vem_pro_culto_api.application.service;

public interface EmailService {

  void send(String to, String subject, String body);
}
