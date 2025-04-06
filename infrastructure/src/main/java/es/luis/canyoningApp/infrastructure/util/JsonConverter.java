package es.luis.canyoningApp.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Use this class as a converter for JSON columns defined in entities.
 *
 * <p>Example:
 *
 * <pre>{@code
 * @Column(name = "INFORMACION_ADICIONAL", nullable = true, columnDefinition = "JSON")
 * @Convert(converter = JsonConverter.class)
 * private Map<String, Object> additionalInformation;
 * }</pre>
 */
@Component
public class JsonConverter implements AttributeConverter<Map<?, ?>, String> {

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Map attribute) {
        if (attribute != null) return objectMapper.writeValueAsString(attribute);
        return null;
    }

    @SneakyThrows
    @Override
    public Map<?, ?> convertToEntityAttribute(String dbData) {
        if (dbData != null) return objectMapper.readValue(dbData, Map.class);
        return null;
    }
}
