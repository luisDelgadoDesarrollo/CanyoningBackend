package es.luis.canyoningApp.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("properties")
public class CanyoningAppConfiguration {

  public CanyoningAppConfiguration() {}

  private String plan;

  private String host;

  private String basePath;

  private String croquis;

  private String controlPoint;
}
