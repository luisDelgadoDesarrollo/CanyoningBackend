package es.luis.canyoningApp.infrastructure.util;

/**
 * Interface used to facilitate the task of mapping enums. An enum consists of an identifier and a
 * name.
 */
public interface EnumEntity {

    Long getId();

    String getName();
}
