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
    private final Map<String, String> properties = new HashMap<>();

    private final String fileName;

    public PropertyContainer(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Загрузка настроек из файла app.properties и их сохранение.
     *
     * @throws IOException возникает, если произошла ошибка при загрузке файла настроек.
     */


    public void loadProperties() {
        var appProperties = new Properties();
        try {
            appProperties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
            for (var entry : appProperties.entrySet()) {
                properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает настройки.
     *
     * @param propertyKey ключ настройки.
     * @return значение настройки по указанному ключу или пустая строка, если ключ не найден.
     */
    public String getProperty(String propertyKey) {
        return properties.getOrDefault(propertyKey, "");
    }
}
