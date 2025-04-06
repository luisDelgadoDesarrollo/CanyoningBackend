package es.luis.canyoningApp.domain.util;

/**
 * Interface used to facilitate the task of mapping enums. An enum consists of an identifier and a
 * name.
 */
public interface EnumType {

    Long getId();

    String getName();

    static <T extends EnumType> T fromId(Long id) {
        // Implement this method in the class
        // return EnumType.fromId(id, T.class);
        throw new UnsupportedOperationException();
    }

    static <T extends EnumType> T fromId(Long id, Class<T> enumType) {
        if (!enumType.isEnum()) {
            throw new IllegalArgumentException("Provided class is not an enum");
        }
        for (T value : enumType.getEnumConstants()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format("id=%s not found for enum %s", id, enumType));
    }
}
