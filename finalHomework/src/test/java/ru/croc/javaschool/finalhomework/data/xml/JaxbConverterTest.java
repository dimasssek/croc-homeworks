package ru.croc.javaschool.finalhomework.data.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finalhomework.dto.AccidentIn;
import ru.croc.javaschool.finalhomework.dto.AccidentListIn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Тест для {@link JaxbConverter}.
 */
public class JaxbConverterTest {
    /**
     * Конвертер.
     */
    private final JaxbConverter converter = new JaxbConverter();

    /**
     * Тест для {@link JaxbConverter#fromXml(String, Class)}.
     * @throws IOException если возникли проблемы с чтением или поиском файла
     */
    @Test
    public void fromXmlTest() throws IOException {
        Path path = Paths.get("src/test/resources", "fromXmlTest.xml");
        AccidentListIn expected = new AccidentListIn(
                new ArrayList<>(List.of(
                        new AccidentIn(
                                LocalDateTime.of(2023, 5, 13, 7, 0, 0),
                                "Столкнулись 2 мусоровоза"
                        ))));
        AccidentListIn actual = converter.fromXml(Files.readString(path), AccidentListIn.class);
        Assertions.assertEquals(expected, actual);
    }
}
