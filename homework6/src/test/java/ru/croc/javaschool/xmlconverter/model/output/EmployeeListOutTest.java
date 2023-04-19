package ru.croc.javaschool.xmlconverter.model.output;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.xmlconverter.converter.JaxbConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Тестирует конвертацию выходных данных для {@link EmployeeListOut}.
 */
public class EmployeeListOutTest {

    @Test
    public void convertEmployeeListToXmlTest() throws IOException {
        EmployeeListOut employeeListOut = new EmployeeListOut();
        employeeListOut.setEmployees(Arrays.asList(
                new EmployeeOut("Человек 1", Arrays.asList(
                    new ProjectOut("Проект 1", "Менеджер")
                )),
                new EmployeeOut("Человек 2", Arrays.asList(
                        new ProjectOut("Проект 1", "Менеджер")
                )),
                new EmployeeOut("Человек 3", Arrays.asList(
                        new ProjectOut("Проект 1", "Специалист", "Человек 1"),
                        new ProjectOut("Проект 2", "Менеджер")
                )),
                new EmployeeOut("Человек 4", Arrays.asList(
                        new ProjectOut("Проект 1", "Специалист", "Человек 1"),
                        new ProjectOut("Проект 2", "Менеджер")
                )),
                new EmployeeOut("Человек 5", Arrays.asList(
                        new ProjectOut("Проект 1", "Специалист", "Человек 2"),
                        new ProjectOut("Проект 2", "Специалист", "Человек 3")
                )),
                new EmployeeOut("Человек 6", Arrays.asList(
                        new ProjectOut("Проект 1", "Специалист", "Человек 2"),
                        new ProjectOut("Проект 2", "Специалист", "Человек 3")
                )),
                new EmployeeOut("Человек 7", Arrays.asList(
                        new ProjectOut("Проект 2", "Специалист", "Человек 4")
                ))
        ));

        final JaxbConverter jaxbConverter = new JaxbConverter();
        final String xml = jaxbConverter.toXml(employeeListOut);
        System.out.println(xml);
        Path outputPath = Paths.get("src/test/resources", "expectedOutput.xml");
        final String output = Files.readString(outputPath);
        Assertions.assertEquals(output, xml);
    }
}
