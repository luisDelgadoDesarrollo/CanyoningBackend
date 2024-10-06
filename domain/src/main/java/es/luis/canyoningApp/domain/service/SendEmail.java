package es.luis.canyoningApp.domain.service;

import es.luis.canyoningApp.domain.model.Canyon;
import jakarta.mail.MessagingException;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

public interface SendEmail {

  void sendPasswordEmail(String email, String token);

  void sendCanyonDocument(PDDocument document, String fileName)
      throws MessagingException, IOException;

  void sendEmailValidatePassword(String email, String token);

  void sendCreateCanyon(Canyon canyon, String authenticatedUsername);

  void sendUpdateCanyon(Canyon oldCanyon, Canyon newCanyon, String authenticatedUsername);
}
