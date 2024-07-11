package es.luis.canyoningApp.infrastructure.util;

/**
 * Interface used to localize entities that have a table with the localized fields of the entity.
 * The fields to be localized must be annotated with @{@link Localize}.
 *
 * @see Localize
 * @see LocalizedEntity
 */
public interface LocalizableEntity {

  /**
   * Class of the entity that have the localized fields.
   *
   * @return The entity class to search the localized entity.
   */
  Class<?> getLocalizedClass();

  /**
   * Returns the primary key of the localized class by language.
   *
   * @param languageId The language identifier.
   * @return The PrimaryKey class to search the entity.
   */
  Object getPrimaryKey(Long languageId);
}
