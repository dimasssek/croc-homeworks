package ru.croc.javaschool.finalhomework.property;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Загрузка настроек из файла app.property.
 */
public class PropertyContainer {
    /**
     * Настройки.
     */
    private final static Map<String, String> properties = new HashMap<>();

    /**
     * Загрузка настроек из файла app.properties и их сохранение.
     *
     * @throws IOException возникает, если произошла ошибка при загрузке файла настроек.
     */
    public static void loadProperties() throws IOException {
        var appProperties = new Properties();
        try {
            appProperties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
            for (var entry : appProperties.entrySet()) {
                properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Возникла ошибка при загрузке настроек");
            throw e;
        }
    }

    /**
     * Возвращает настройки.
     *
     * @param propertyKey ключ настройки.
     * @return значение настройки по указанному ключу или пустая строка, если ключ не найден.
     */
    public static String getProperty(String propertyKey) {
        return properties.getOrDefault(propertyKey, "");
    }
}
