package es.luis.canyoningApp.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.luis.canyoningApp.common.CanyoningAppConfiguration;
import es.luis.canyoningApp.domain.model.Canyon;
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

    @Autowired
    ObjectMapper objectMapper;

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

    @Override
    public void sendCreateCanyon(Canyon canyon, String authenticatedUsername) {
        // Crear el mensaje MimeMessage para contenido HTML
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // Utilizar MimeMessageHelper para configurar el mensaje con soporte HTML
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("documentos@misbarrancosbdesarrollo.com");
            helper.setTo("delgadofernandez.luisdesarrollo@gmail.com");
            helper.setSubject("Crear barranco " + canyon.getCanyonId());
            // Crear el cuerpo del correo con etiquetas HTML
            String htmlContent =
                    "<h1>Barranco creado </h1>"
                            + "<p>Barranco creado por <strong>"
                            + authenticatedUsername
                            + "</strong></p>"
                            + "<p>Detalles del barranco:</p>"
                            + "<pre>"
                            + objectMapper.writeValueAsString(canyon)
                            + "</pre>";

            // Establecer el cuerpo del correo con HTML
            helper.setText(htmlContent, true); // true para indicar que es HTML

            // Enviar el correo
            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e); // Manejar excepción
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendUpdateCanyon(Canyon oldCanyon, Canyon newCanyon, String authenticatedUsername) {
        // Crear el mensaje MimeMessage para contenido HTML
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // Utilizar MimeMessageHelper para configurar el mensaje con soporte HTML
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("documentos@misbarrancosbdesarrollo.com");
            helper.setTo("delgadofernandez.luisdesarrollo@gmail.com");
            helper.setSubject("Actualizar barranco " + oldCanyon.getCanyonId());

            // Crear el cuerpo del correo con etiquetas HTML
            String htmlContent =
                    "<h1>Barranco actualizado </h1>"
                            + "<p>Barranco actualizado por <strong>"
                            + authenticatedUsername
                            + "</strong></p>"
                            + "<h2>Detalles del barranco antiguo:</h2>"
                            + "<pre>"
                            + objectMapper.writeValueAsString(oldCanyon)
                            + "</pre>"
                            + "<h2>Detalles del barranco nuevo:</h2>"
                            + "<pre>"
                            + objectMapper.writeValueAsString(newCanyon)
                            + "</pre>";

            // Establecer el cuerpo del correo con HTML
            helper.setText(htmlContent, true); // true para indicar que es HTML

            // Enviar el correo
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace(); // Manejar excepción
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendImageChange(String string) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            // Utilizar MimeMessageHelper para configurar el mensaje con soporte HTML
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("documentos@misbarrancosbdesarrollo.com");
            helper.setTo("delgadofernandez.luisdesarrollo@gmail.com");
            helper.setSubject("Cambio de imagen");

            // Crear el cuerpo del correo con etiquetas HTML

            // Establecer el cuerpo del correo con HTML
            helper.setText(string, true); // true para indicar que es HTML

            // Enviar el correo
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace(); // Manejar excepción
        }
    }
}
