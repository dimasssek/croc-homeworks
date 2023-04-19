package ru.croc.javaschool.xmlconverter.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.xmlconverter.model.input.ProjectListIn;
import ru.croc.javaschool.xmlconverter.model.output.EmployeeListOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Тест для {@link ProjectsToPeopleConvert}.
 */
public class ProjectsToPeopleConvertTest {

    private final ProjectsToPeopleConvert converter = new ProjectsToPeopleConvert();

    @Test
    public void convertTest() throws IOException {
        Path inputPath = Paths.get("src/test/resources", "inputList.xml");
        Path outputPath = Paths.get("src/test/resources", "expectedOutput.xml");
        final String input = Files.readString(inputPath);
        final String expected = Files.readString(outputPath);
        final JaxbConverter jaxbConverter = new JaxbConverter();

        ProjectListIn projectList = jaxbConverter.fromXml(input, ProjectListIn.class);
        final String xml = jaxbConverter.toXml(converter.convert(projectList));
        Assertions.assertEquals(expected, xml);
        Assertions.assertEquals(
                jaxbConverter.fromXml(expected, EmployeeListOut.class),
                jaxbConverter.fromXml(xml, EmployeeListOut.class)
        );
    }
}
