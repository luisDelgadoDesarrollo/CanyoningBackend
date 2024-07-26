package es.luis.canyoningApp.domain.service;

public interface SendEmail {

  void sendPasswordEmail(String email, String token);
}
