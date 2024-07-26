package es.luis.canyoningApp.boot.config.boot;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp-mail.outlook.com");
    mailSender.setPort(587);

    mailSender.setUsername("canyoningApp@hotmail.com");
    mailSender.setPassword("Luis884994??");

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    return mailSender;
  }

  @Bean
  public SimpleMailMessage templateSimpleMessage() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setText(
        "Estimado/a usuario/a,\n"
            + "\n"
            + "Recibimos una solicitud para restablecer su contraseña. Si no realizó esta solicitud, por favor ignore este correo electrónico. De lo contrario, haga clic en el siguiente enlace para cambiar su contraseña:\n"
            + "\n"
            + "\n%s\n"
            + "\n"
            + "Por motivos de seguridad, este enlace será válido por solo 24 horas. Si el enlace ha expirado, deberá solicitar un nuevo enlace de restablecimiento.\n"
            + "\n"
            + "Gracias,\n"
            + "El equipo de soporte\n"
            + "\n"
            + "Este es un correo electrónico generado automáticamente, por favor no responda a este mensaje.");
    return message;
  }
}
