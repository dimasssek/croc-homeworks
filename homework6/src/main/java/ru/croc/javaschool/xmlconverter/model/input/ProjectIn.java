package ru.croc.javaschool.xmlconverter.model.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * Проект.
 */
public class ProjectIn {
    /**
     * Название.
     */
    @XmlElement(name = "title")
    private String title;
    /**
     * Описание.
     */
    @XmlElement(name = "description")
    private String description;
    /**
     * Список менеджеров.
     */
    @XmlElement(name = "manager")
    @XmlElementWrapper(name = "managers")
    private List<EmployeeIn> managers;

    public ProjectIn() {
    }

    /**
     * Создаёт {@link ProjectIn}.
     * @param title название
     * @param description описание
     * @param managers менеджеры
     */
    public ProjectIn(String title, String description, List<EmployeeIn> managers) {
        this.title = title;
        this.description = description;
        this.managers = managers;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<EmployeeIn> getManagers() {
        return managers;
    }

    @Override
    public String toString() {
        return "ProjectIn{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", managers=" + managers +
                '}';
    }
}
