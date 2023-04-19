package ru.croc.javaschool.xmlconverter.model.input;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список проектов, поступающий на вход.
 */
@XmlRootElement(name = "projects")
public class ProjectListIn {
    /**
     * Проекты.
     */

    @XmlElement(name = "project")
    private List<ProjectIn> projects;

    public ProjectListIn() {
    }

    /**
     * Добавление проекта в список.
     * @param project проект
     */
    public void addProject(ProjectIn project) {
        projects.add(project);
    }

    public List<ProjectIn> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectIn> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "ProjectListIn{" +
                "projects=" + projects +
                '}';
    }
}
