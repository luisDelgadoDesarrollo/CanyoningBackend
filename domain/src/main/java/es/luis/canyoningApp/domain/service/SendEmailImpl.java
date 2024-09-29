package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import es.luis.canyoningApp.domain.util.AuthenticatedUserBase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
public class SendEmailImpl extends AuthenticatedUserBase implements SendEmail {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage simpleMailMessage;

    @Autowired
    CanyoningAppConfiguration canyoningAppConfiguration;

    @Override
    public void sendPasswordEmail(String email, String token) {
        String endpoint =
                canyoningAppConfiguration.getRecoverPassword()
                        + "/updatePassword?email="
                        + email
                        + "&token="
                        + token;
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

    @Override
    public void sendEmailValidatePassword(String email, String token) {
        String endpoint = canyoningAppConfiguration.getHost() + "/validateUser?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("documentos@misbarrancosbdesarrollo.com");
        message.setTo(email);
        message.setSubject("Validar Usuario");
        message.setText(
                "Haga click en el enlace para validar la cuenta y que pueda acceder a la aplicacion, si no se ha registrado ignore este correo\n"
                        + endpoint);
        javaMailSender.send(message);
    }
}
