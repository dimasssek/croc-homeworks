package ru.croc.javaschool.finalhomework.property;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Тест {@link PropertyContainer}
 */
public class PropertyContainerTest {
    /**
     * Загрузка настроек из файла.
     *
     * @throws IOException если произошла ошибка при загрузке настроек.
     */
    @BeforeAll
    static void loadProperties() throws IOException {
        PropertyContainer.loadProperties();
    }

    /**
     * Проверяет, что метод возвращает корректные настройки.
     */
    @Test
    public void getPropertyTest() {
        Assertions.assertEquals("accidents_db", PropertyContainer.getProperty("database.name"));
        Assertions.assertEquals("root", PropertyContainer.getProperty("database.username"));
        Assertions.assertEquals("root", PropertyContainer.getProperty("database.password"));
    }

}
