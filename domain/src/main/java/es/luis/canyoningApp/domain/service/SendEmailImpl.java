package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmailImpl implements SendEmail {

  @Autowired JavaMailSender javaMailSender;

  @Autowired SimpleMailMessage simpleMailMessage;

  @Autowired CanyoningAppConfiguration canyoningAppConfiguration;

  @Override
  public void sendPasswordEmail(String email, String token) {
    String endpoint =
        canyoningAppConfiguration.getHost() + "/updatePassword?email=" + email + "&token=" + token;
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("canyoningApp@hotmail.com");
    message.setTo(email);
    message.setSubject("Recuperar contrasena");
    message.setText(String.format(Objects.requireNonNull(simpleMailMessage.getText()), endpoint));
    javaMailSender.send(message);
  }
}
