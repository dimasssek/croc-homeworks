package ru.croc.javaschool.xmlconverter.model.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Сотрудник, поступающий на выход.
 */
public class EmployeeOut {
    /**
     * Имя.
     */
    @XmlElement
    private String name;
    /**
     * Список проектов, в которых он участвует.
     */
    @XmlElement(name = "project")
    @XmlElementWrapper(name = "projects")
    private List<ProjectOut> projects;

    public EmployeeOut() {}

    /**
     *
     * @param name имя
     * @param projects проекты
     */
    public EmployeeOut(String name, List<ProjectOut> projects) {
        this.name = name;
        this.projects = projects;
    }

    /**
     *
     * @param name имя
     */
    public EmployeeOut(String name) {
        this.name = name;
        this.projects = new ArrayList<>();
    }

    public void addProject(ProjectOut project) {
        projects.add(project);
    }

    public String getName() {
        return name;
    }


}
