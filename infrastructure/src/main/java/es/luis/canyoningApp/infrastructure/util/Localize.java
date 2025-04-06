package es.luis.canyoningApp.infrastructure.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to localize fields of classes with the fields of another class. It is
 * used in conjunction with {@link LocalizedEntity} to use the constructor of which localize the
 * class with the localization class. To localize inner types that should be searched in database
 * must implement {@link LocalizableEntity} interface.
 *
 * @see LocalizedEntity
 * @see LocalizableEntity
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Localize {

    /**
     * The name of the field of the localized class that have the translation of the annotated field.
     * If not provided, the value will be equal to the field name.
     */
    String fieldName() default "";

    /**
     * If true, the value of the localized class will not be copied if it is null. If false, the value
     * of the localized class will be copied regardless of whether the value is null.
     */
    boolean ignoreNull() default true;
}
