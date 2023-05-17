package ru.croc.javaschool.finalhomework.data.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * Тест {@link LocalDateTimeConverter}.
 */

public class LocalDateTimeConverterTest {
    /**
     * Тестовый объект LocalDateTime.
     */
    private final LocalDateTime time = LocalDateTime.of(2023, 5, 15, 20, 35, 0);
    /**
     * Конвертер.
     */
    private final LocalDateTimeConverter converter = new LocalDateTimeConverter();

    /**
     * Тест для {@link LocalDateTimeConverter#marshal(LocalDateTime)}. Проверяет преобразование в строку.
     * @throws Exception если произошла ошибка преобразования
     */
    @Test
    public void marshalTest() {
        String expected = "2023-05-15T20:35:00";
        String actual = converter.marshal(time);
        Assertions.assertEquals(expected, actual);
    }
    /**
     * Тест для {@link LocalDateTimeConverter#unmarshal(String)}. Проверяет преобразование в строку.
     * @throws Exception если произошла ошибка преобразования
     */
    @Test
    public void unmarshalTest() {
        LocalDateTime actual = converter.unmarshal("2023-05-15T20:35:00");
        Assertions.assertEquals(time, actual);
    }
}
