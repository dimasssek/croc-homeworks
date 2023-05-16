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

    @Test
    public void marshalTest() throws Exception {
        String expected = "2023-05-15T20:35:00";
        String actual = converter.marshal(time);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void unmarshalTest() throws Exception {
        LocalDateTime actual = converter.unmarshal("2023-05-15T20:35:00");
        Assertions.assertEquals(time, actual);
    }
}
