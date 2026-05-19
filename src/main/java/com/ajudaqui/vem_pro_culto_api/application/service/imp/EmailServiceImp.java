package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import com.ajudaqui.vem_pro_culto_api.application.service.EmailService;
import com.ajudaqui.vem_pro_culto_api.domain.gateway.EmailGateway;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImp implements EmailService {

  private final EmailGateway emailGateway;

  @Override
  public void send(String to, String subject, String body) {
    System.out.printf("enviando para: %s, subject: $s corpo: $s",
        to, subject, body);
    emailGateway.send(to, subject, body);
  }

}
