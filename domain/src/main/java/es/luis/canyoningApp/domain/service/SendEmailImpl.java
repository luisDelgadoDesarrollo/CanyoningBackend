package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import es.luis.canyoningApp.domain.util.AuthenticatedUserBase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmailImpl extends AuthenticatedUserBase implements SendEmail {

  @Autowired JavaMailSender javaMailSender;

  @Autowired SimpleMailMessage simpleMailMessage;

  @Autowired CanyoningAppConfiguration canyoningAppConfiguration;

  @Override
  public void sendPasswordEmail(String email, String token) {
    String endpoint =
        canyoningAppConfiguration.getHost() + "/updatePassword?email=" + email + "&token=" + token;
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("documentos@misbarrancosbdesarrollo.com");
    message.setTo(email);
    message.setSubject("Recuperar contrasena");
    message.setText(String.format(Objects.requireNonNull(simpleMailMessage.getText()), endpoint));
    javaMailSender.send(message);
  }

  @Override
  public void sendCanyonDocument(PDDocument document, String fileName)
      throws MessagingException, IOException {

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setFrom("documentos@misbarrancosbdesarrollo.com");
    helper.setTo(getAuthenticatedUsername());
    helper.setSubject("Ficha del barranco " + fileName.split("\\.")[0]);
    helper.setText("Documento con la informacion del barranco");

    // Convertir el PDDocument a un ByteArrayOutputStream
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    document.save(byteArrayOutputStream);
    byte[] pdfBytes = byteArrayOutputStream.toByteArray();

    // Adjuntar el PDF al correo
    helper.addAttachment(fileName, new ByteArrayDataSource(pdfBytes, "application/pdf"));

    javaMailSender.send(message);
  }
}
