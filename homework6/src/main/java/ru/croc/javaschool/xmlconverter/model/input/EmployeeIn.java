package ru.croc.javaschool.xmlconverter.model.input;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Сотрудник.
 */
public class EmployeeIn {
    /**
     * Имя.
     */
    @XmlAttribute
    private String name;
    /**
     * Список подчиненных.
     */
    @XmlElement(name = "specialist")
    @XmlElementWrapper(name = "specialists")
    private List<EmployeeIn> specialists;

    public EmployeeIn() {}

    /**
     *
     * @param name имя
     * @param specialists подчиненные
     */
    public EmployeeIn(String name, List<EmployeeIn> specialists) {
        this.name = name;
        this.specialists = specialists;
    }

    public EmployeeIn(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public List<EmployeeIn> getSpecialists() {
        return specialists;
    }
}
