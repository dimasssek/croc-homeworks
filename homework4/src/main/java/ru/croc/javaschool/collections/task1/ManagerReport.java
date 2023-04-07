package ru.croc.javaschool.collections.task1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Comparator;

/**
 * Отчёт о сотрудниках.
 */
public class ManagerReport {
    /**
     * Список сотрудников.
     */
    private final List<Employee> employees;

    /**
     * Создаёт {@link ManagerReport}.
     *
     * @param employees список сотрудников.
     */
    public ManagerReport(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Разделение сотрудников по разным организациям, основываясь на поле manager.
     * Реализуется задача поиска компонент связности графа без поиска в глубину или ширирну.
     *
     * @return список организаций, где ключ - идентификатор руководителя, а значение - список сотрудников отдела,
     * включая руководителя отдела.
     */
    public Map<Integer, List<Employee>> getOrganizations() {

        Map<Integer, List<Employee>> organizations = new HashMap<>();
        for (Employee employee : employees) {
            Employee currentManager = null;
            if (Objects.isNull(employee.getManager())) {
                currentManager = employee;
            } else if (isManagerInList(employee.getManager())) {
                currentManager = employee.getManager();
                while (Objects.nonNull(currentManager.getManager())) {
                    currentManager = currentManager.getManager();
                }
            }

            if (currentManager != null) {
                if (!organizations.containsKey(currentManager.getId())) {
                    organizations.put(currentManager.getId(), new ArrayList<>());
                }
                organizations.get(currentManager.getId()).add(employee);
            }
        }

        sortEmployeesByDepartment(organizations);
        return organizations;
    }

    /**
     * На основе списка сотрудников строит рейтинг. Ключ - сотрудник,
     * значение - количество сотрудников, находящихся у него в подчинении.
     * <p>
     * Реализуется задача поиска исходящих степеней вершин графа. Также в рейтинг попадают изолированные вершины.
     * @return отсортированный рейтинг сотрудников вида {@link Map}<{@link Employee}, {@link Integer}>.
     */
    public Map<Employee, Integer> getManagersRating() {
        Map<Employee, List<Employee>> adjacencyList = buildAdjacencyList();
        Map<Employee, Integer> managersRating = new LinkedHashMap<>();

        for (Map.Entry<Employee, List<Employee>> entry : adjacencyList.entrySet()) {
            managersRating.put(entry.getKey(), entry.getValue().size());
        }

        return getSortedManagersRating(managersRating);
    }

    /**
     * Сортировка списка сотрудников отдела по идентификационным номерам.
     *
     * @param organizations список отделов.
     */
    private void sortEmployeesByDepartment(Map<Integer, List<Employee>> organizations) {
        for (Map.Entry<Integer, List<Employee>> entry : organizations.entrySet()) {
            List<Employee> employeesOfDepartment = entry.getValue();
            employeesOfDepartment.sort(Comparator.comparing(Employee::getId));
        }
    }

    /**
     * Вспомогательная функция. Проверка наличия руководителя в списке сотрудников.
     *
     * @param manager руководитель.
     * @return true если есть, else иначе.
     */
    private boolean isManagerInList(Employee manager) {
        for (Employee employee : employees) {
            if (employee.getId().equals(manager.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Вспомогательная функция. Выполняет построение списка смежности изначального графа(списка сотрудников).
     *
     * @return список смежности, где ключ - это сотрудник, а значение - список находящихся у него в подчинении
     * сотрудников.
     */
    private Map<Employee, List<Employee>> buildAdjacencyList() {
        Map<Employee, List<Employee>> adjacencyList = new LinkedHashMap<>();

        for (Employee employee : employees) {
            adjacencyList.put(employee, new ArrayList<>());
        }

        for (Employee employee : employees) {
            Employee manager = employee.getManager();
            while (manager != null) {
                if (!adjacencyList.get(manager).contains(employee)) {
                    adjacencyList.get(manager).add(employee);
                }
                manager = manager.getManager();
            }
        }
        return adjacencyList;
    }

    /**
     * Вспомогательная функция. Сортирует рейтинг сотрудников по количеству сотрудников. Если значение равно,
     * то выполняется лексикографическая сортировка по именам.
     * @param unsortedManagersRating неотсортированный рейтинг сотрудников.
     * @return отсортированный рейтинг сотрудников.
     */
    private Map<Employee, Integer> getSortedManagersRating(Map<Employee, Integer> unsortedManagersRating) {
        List<Map.Entry<Employee, Integer>> entryList = new ArrayList<>(unsortedManagersRating.entrySet());

        entryList.sort(new Comparator<>() {
            public int compare(Map.Entry<Employee, Integer> employee1,
                               Map.Entry<Employee, Integer> employee2) {
                return employee2.getValue().compareTo(employee1.getValue());
            }
        });

        Map<Employee, Integer> sortedManagersRating = new LinkedHashMap<>();
        for (Map.Entry<Employee, Integer> entry : entryList) {
            sortedManagersRating.put(entry.getKey(), entry.getValue());
        }

        return sortedManagersRating;
    }
}
