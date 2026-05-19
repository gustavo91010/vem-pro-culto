package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import com.ajudaqui.vem_pro_culto_api.application.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImp implements EmailService {

  private final JavaMailSender mailSender;

  @Override
  public void send(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);
    mailSender.send(message);
  }

}
