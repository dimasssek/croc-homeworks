package ru.croc.javaschool.xmlconverter.model.output;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список сотрудников, поступающий на выход.
 */
@XmlRootElement(name = "people")
public class EmployeeListOut {
    /**
     * Список сотрудников.
     */
    @XmlElement(name = "person")
    private List<EmployeeOut> employees;

    public EmployeeListOut() {
    }

    /**
     *
     * @param employees сотрудники
     */
    public EmployeeListOut(List<EmployeeOut> employees) {
        this.employees = employees;
    }

    public void addEmployee(EmployeeOut employee) {
        employees.add(employee);
    }

    public void setEmployees(List<EmployeeOut> employees) {
        this.employees = employees;
    }
}
