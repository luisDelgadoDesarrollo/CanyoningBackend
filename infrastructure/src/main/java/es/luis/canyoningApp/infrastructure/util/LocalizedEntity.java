package es.luis.canyoningApp.infrastructure.util;

import jakarta.persistence.EntityManager;
import java.lang.reflect.Field;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Class to implement the localization of entities. The classes that extends this class should
 * implement a constructor that use this class constructor to copy the values of the localized
 * entity to the main entity and search into the database for inner classes that implements {@link
 * LocalizableEntity}. The desired values to be copied from one class to another has to be annotated
 * with @{@link Localize}.
 *
 * @see Localize
 * @see LocalizableEntity
 */
public abstract class LocalizedEntity {

  private final EntityManager entityManager = EntityManagerContextHolder.getInstance();

  public LocalizedEntity() {}

  private LocalizedEntity(LocalizedEntity entity) {
    BeanUtils.copyProperties(entity, this);
  }

  /**
   * This constructor can be used for class localization needs. It searches for localized fields and
   * copies the values from localized class to the entity class and construct the object.
   *
   * @param entity The main class that is annotated with @{@link Localize}.
   * @param languageId The language identifier
   */
  public LocalizedEntity(LocalizedEntity entity, Long languageId) {
    this(entity);
    ofLocalization(this, null, languageId);
  }

  /**
   * This constructor can be used for class localization needs. It copies the values from localized
   * class to the entity class and construct the object.
   *
   * @param entity The main class that is annotated with @{@link Localize}.
   * @param localization The class that have the translation of annotated fields.
   * @param languageId The language identifier
   */
  public LocalizedEntity(LocalizedEntity entity, Object localization, Long languageId) {
    this(entity);
    ofLocalization(this, localization, languageId);
  }

  /**
   * This method copy to the fields of the entity class annotated with @{@link Localize} the values
   * from localization class.
   *
   * @param entity The main class that is annotated with @{@link Localize}.
   * @param localization The class that have the translation of annotated fields.
   * @param languageId The language identifier.
   * @return The entity class.
   */
  @SneakyThrows
  public <T> T ofLocalization(T entity, Object localization, Long languageId) {
    Assert.notNull(entity, "Entity is null");

    // Go through all entity fields searching for @Localize annotation
    for (Field field : entity.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Localize.class)) {
        Localize localize = field.getAnnotation(Localize.class);
        ReflectionUtils.makeAccessible(field);
        // Retrieve the field to localize
        Object innerEntity = ReflectionUtils.getField(field, entity);
        // If the field is localizable, search in the DB for the localized class
        if (innerEntity instanceof LocalizableEntity localizableEntity) {
          Object localizedValue =
              entityManager.find(
                  localizableEntity.getLocalizedClass(),
                  localizableEntity.getPrimaryKey(languageId));
          ReflectionUtils.setField(
              field, entity, ofLocalization(innerEntity, localizedValue, languageId));
        } else if (localization != null
            && innerEntity instanceof String) { // Get the field from the localization object
          String fieldName = localize.fieldName();
          if (ObjectUtils.isEmpty(fieldName)) {
            fieldName = field.getName();
          }
          Field localizationField = ReflectionUtils.findField(localization.getClass(), fieldName);
          Assert.notNull(
              localizationField,
              "Field " + fieldName + " not exists in " + localization.getClass());
          ReflectionUtils.makeAccessible(localizationField);
          Object localizedValue = ReflectionUtils.getField(localizationField, localization);
          if (localizedValue != null || !localize.ignoreNull()) {
            ReflectionUtils.setField(field, entity, localizedValue);
          }
        }
      }
    }

    return entity;
  }
}
