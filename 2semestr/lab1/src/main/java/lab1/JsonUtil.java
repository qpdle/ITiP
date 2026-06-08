package lab1;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
    // ObjectMapper - главный класс Jackson для работы с JSON
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // Приватный конструктор, чтобы нельзя было создать экземпляр класса
    private JsonUtil() {}

    // Сериализация: объект в JSON-строку
    public static String toJson(Person p) {
        try {
            return MAPPER.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    // Десериализация: JSON-строка в объект
    public static Person fromJson(String json) {
        try {
            return MAPPER.readValue(json, Person.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }
}