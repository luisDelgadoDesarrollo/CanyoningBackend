package es.luis.canyoningApp.domain.util;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;

public class ServiceUtils {
  private static final int TOKEN_LENGTH = 16; // Longitud del token en bytes
  private static final long EXPIRATION_HOURS = 24; // Tiempo de expiración en horas

  public static String generateToken() {
    // Generar token aleatorio usando SecureRandom
    SecureRandom secureRandom = new SecureRandom();
    byte[] tokenBytes = new byte[TOKEN_LENGTH];
    secureRandom.nextBytes(tokenBytes);

    // Convertir bytes aleatorios a una cadena base64
    String token = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);

    return token;
  }

  public static OffsetDateTime calculateExpiration() {
    // Calcular la fecha y hora de expiración del token
    return OffsetDateTime.now().plusHours(EXPIRATION_HOURS);
  }
}
