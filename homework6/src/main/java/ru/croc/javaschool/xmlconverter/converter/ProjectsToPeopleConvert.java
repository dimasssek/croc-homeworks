package ru.croc.javaschool.xmlconverter.converter;

import ru.croc.javaschool.xmlconverter.model.input.EmployeeIn;
import ru.croc.javaschool.xmlconverter.model.input.ProjectIn;
import ru.croc.javaschool.xmlconverter.model.input.ProjectListIn;
import ru.croc.javaschool.xmlconverter.model.output.EmployeeListOut;
import ru.croc.javaschool.xmlconverter.model.output.EmployeeOut;
import ru.croc.javaschool.xmlconverter.model.output.ProjectOut;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертация списка проектов в список людей.
 */
public class ProjectsToPeopleConvert {

    public EmployeeListOut convert(ProjectListIn inputProjects) {
        List<EmployeeOut> outputEmployees = new ArrayList<>();
        List<ProjectIn> projectInList = inputProjects.getProjects();
        for (ProjectIn project : projectInList) {

            for (EmployeeIn manager : project.getManagers()) {
                int indexOfEmployee = indexEmployeeInList(outputEmployees, manager);
                if (indexOfEmployee != -1) {
                    ProjectOut projectOut = new ProjectOut(project.getTitle(), "Менеджер");
                    outputEmployees.get(indexOfEmployee).addProject(projectOut);
                } else {
                    EmployeeOut employeeOut = new EmployeeOut(manager.getName());
                    ProjectOut projectOut = new ProjectOut(project.getTitle(), "Менеджер");
                    employeeOut.addProject(projectOut);
                    outputEmployees.add(employeeOut);
                }
            }
            for (EmployeeIn manager : project.getManagers()) {
                for (EmployeeIn specialist : manager.getSpecialists()) {
                    EmployeeOut specialistOut = new EmployeeOut(specialist.getName());
                    ProjectOut specialistsProjectOut = new ProjectOut(project.getTitle(), "Специалист");
                    specialistOut.addProject(specialistsProjectOut);
                    outputEmployees.add(specialistOut);
                }
            }
        }
        return new EmployeeListOut(outputEmployees);
    }

    /**
     * Вспомогательная функция. Проверяет наличие человека в списке.
     *
     * @param outputEmployees выходной список сотрудников
     * @param employee        сотрудник входного списка
     * @return индекс, если найден, -1 если не найден или список пуст
     */
    private int indexEmployeeInList(List<EmployeeOut> outputEmployees, EmployeeIn employee) {
        if (outputEmployees.isEmpty()) {
            return -1;
        }
        int index = 0;
        for (EmployeeOut employeeOut : outputEmployees) {
            if (employeeOut.getName() == employee.getName())
                return index;
            index++;
        }
        return -1;
    }
}
