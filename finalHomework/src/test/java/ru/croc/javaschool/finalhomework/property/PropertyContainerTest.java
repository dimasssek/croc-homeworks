package ru.croc.javaschool.finalhomework.property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Тест {@link PropertyContainer}
 */
public class PropertyContainerTest {
    /**
     * Настройки.
     */
    private final PropertyContainer propertyContainer = new PropertyContainer("app_test.properties");

    /**
     * Проверяет, что метод возвращает корректные настройки.
     */
    @Test
    public void getPropertyTest() throws IOException {
        propertyContainer.loadProperties();
        Assertions.assertEquals("accident_db_test", propertyContainer.getProperty("database.name"));
        Assertions.assertEquals("root", propertyContainer.getProperty("database.username"));
        Assertions.assertEquals("root", propertyContainer.getProperty("database.password"));
    }

}
