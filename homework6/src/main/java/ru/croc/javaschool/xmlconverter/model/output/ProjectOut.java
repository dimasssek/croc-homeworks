package ru.croc.javaschool.xmlconverter.model.output;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Проект, поступающий на выход.
 */
public class ProjectOut {
    /**
     * Название.
     */
    @XmlAttribute
    private String title;

    @XmlElement(name = "role")
    private String role;

    @XmlElement(name = "manager")
    private String manager;

    public ProjectOut() {
    }

    public ProjectOut(String title, String role) {
        this.title = title;
        this.role = role;
        this.manager = "";
    }

    public ProjectOut(String title, String role, String manager) {
        this(title, role);
        this.manager = manager;
    }
}