package ru.croc.javaschool.xmlconverter.converter;

import ru.croc.javaschool.xmlconverter.model.input.EmployeeIn;
import ru.croc.javaschool.xmlconverter.model.input.ProjectIn;
import ru.croc.javaschool.xmlconverter.model.input.ProjectListIn;
import ru.croc.javaschool.xmlconverter.model.output.EmployeeListOut;
import ru.croc.javaschool.xmlconverter.model.output.EmployeeOut;
import ru.croc.javaschool.xmlconverter.model.output.ProjectOut;

import java.util.*;

/**
 * Конвертация списка проектов в список людей.
 */
public class ProjectsToPeopleConvert {
    /**
     * Преобразует данные о проектах в данные о людях.
     *
     * @param inputProjects проекты
     * @return люди
     */
    public EmployeeListOut convert(ProjectListIn inputProjects) {
        Map<String, EmployeeOut> mapOfNameAndEmployee = new LinkedHashMap<>();
        List<ProjectIn> projectInList = inputProjects.getProjects();
        for (ProjectIn project : projectInList) {
            for (EmployeeIn manager: project.getManagers()) {
                for (EmployeeIn specialist: manager.getSpecialists()) {
                    EmployeeOut employeeOut;
                    if (!mapOfNameAndEmployee.containsKey(specialist.getName())) {
                        employeeOut = new EmployeeOut(specialist.getName());
                    } else {
                        employeeOut = mapOfNameAndEmployee.get(specialist.getName());
                    }
                    ProjectOut projectOut = new ProjectOut(project.getTitle(), "Специалист", manager.getName());
                    employeeOut.addProject(projectOut);
                    mapOfNameAndEmployee.put(specialist.getName(), employeeOut);
                }
                EmployeeOut employeeOut;
                if (!mapOfNameAndEmployee.containsKey(manager.getName())) {
                    employeeOut = new EmployeeOut(manager.getName());
                } else {
                    employeeOut = mapOfNameAndEmployee.get(manager.getName());
                }
                ProjectOut projectOut = new ProjectOut(project.getTitle(), "Менеджер");
                employeeOut.addProject(projectOut);
                mapOfNameAndEmployee.put(manager.getName(), employeeOut);
            }
        }

        List<Map.Entry<String, EmployeeOut>> sortMap = new ArrayList<>(mapOfNameAndEmployee.entrySet());
        sortMap.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, EmployeeOut> o1, Map.Entry<String, EmployeeOut> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        mapOfNameAndEmployee.clear();
        for (Map.Entry<String, EmployeeOut> entry: sortMap) {
            mapOfNameAndEmployee.put(entry.getKey(), entry.getValue());
        }
        return new EmployeeListOut(new ArrayList<>(mapOfNameAndEmployee.values()));
    }
}
