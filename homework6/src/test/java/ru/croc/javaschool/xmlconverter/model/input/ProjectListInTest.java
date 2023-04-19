package ru.croc.javaschool.xmlconverter.model.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.xmlconverter.converter.JaxbConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Тестирует конвертацию входных данных для {@link ProjectListIn}.
 */
public class ProjectListInTest {

    @Test
    public void convertProjectsToXmlTest() throws IOException {
        ProjectListIn projectListIn = new ProjectListIn();
        projectListIn.setProjects(Arrays.asList(
                new ProjectIn("Проект 1",
                        "Описание 1",
                        Arrays.asList(
                                new EmployeeIn("Человек 1",
                                        Arrays.asList(
                                                new EmployeeIn("Человек 3"),
                                                new EmployeeIn("Человек 4")
                                        )),
                                new EmployeeIn("Человек 2",
                                        Arrays.asList(
                                                new EmployeeIn("Человек 5"),
                                                new EmployeeIn("Человек 6")
                                        )))),
                new ProjectIn("Проект 2",
                        "Описание 2",
                        Arrays.asList(
                                new EmployeeIn("Человек 3",
                                        Arrays.asList(
                                                new EmployeeIn("Человек 5"),
                                                new EmployeeIn("Человек 6")
                                        )),
                                new EmployeeIn("Человек 4",
                                        Arrays.asList(
                                                new EmployeeIn("Человек 7")
                                        ))))
        ));

        final JaxbConverter jaxbConverter = new JaxbConverter();
        final String xml = jaxbConverter.toXml(projectListIn);
        System.out.println(xml);
        Path inputPath = Paths.get("src/test/resources", "inputList.xml");
        final String input = Files.readString(inputPath);
        Assertions.assertEquals(input, xml);
    }
}
