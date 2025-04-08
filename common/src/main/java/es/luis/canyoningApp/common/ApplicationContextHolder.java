package es.luis.canyoningApp.common;

import lombok.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * This class holds the Spring Application Context to be used in non-bean classes that cannot use
 * the main way of getting beans with @Autowired or any other required method from Spring Context.
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(@NonNull ApplicationContext applicationContext)
      throws BeansException {
    ApplicationContextHolder.applicationContext = applicationContext;
  }

  public static ApplicationContext getInstance() {
    return applicationContext;
  }
}
