package ru.croc.javaschool.collections.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Тест для {@link ManagerReport}.
 */
public class ManagerReportTest {
    /**
     * Смешанный список сотрудников.
     */
    private final List<Employee> employees = new ArrayList<>();
    /**
     * Сотрудник.
     */
    private Employee
            employee1Chef,
            employee2,
            employee3,
            employee4,
            employee5,
            employee6Chef,
            employee7,
            employee8,
            employee9,
            employee10,
            employee11;

    /**
     * Первый вариант заполнения списка.
     */
    private void firstVariantToAdd() {
        employees.clear();
        employees.add(employee5);
        employees.add(employee2);
        employees.add(employee10);
        employees.add(employee4);
        employees.add(employee7);
        employees.add(employee11);
        employees.add(employee1Chef);
        employees.add(employee3);
        employees.add(employee6Chef);
        employees.add(employee8);
        employees.add(employee9);
    }
    /**
     * Второй вариант заполнения списка.
     */
    private void secondVariantToAdd() {
        employees.clear();
        employees.add(employee2);
        employees.add(employee5);
        employees.add(employee6Chef);
        employees.add(employee4);
        employees.add(employee7);
        employees.add(employee10);
        employees.add(employee9);
        employees.add(employee3);
        employees.add(employee11);
        employees.add(employee8);
        employees.add(employee1Chef);
    }
    /**
     * Начальная инициализация сотрудников.
     */
    @BeforeEach
    public void init() {
        employee1Chef = new Employee(1, "Gus");
        employee2 = new Employee(2, "Walter", employee1Chef);
        employee3 = new Employee(3, "Mike", employee1Chef);
        employee4 = new Employee(4, "Jessy", employee2);
        employee5 = new Employee(5, "Saul", employee2);

        employee6Chef = new Employee(6, "Skyler");
        employee7 = new Employee(7, "Flint", employee6Chef);
        employee8 = new Employee(8, "Mary", employee6Chef);
        employee9 = new Employee(9, "Hank", employee8);
        employee10 = new Employee(10, "Steven", employee9);
        employee11 = new Employee(11, "Tuco", employee9);

    }

    /**
     * Тест метода, который формирует списки организаций, основываясь на поле manager.
     */
    @Test
    public void getOrganisationsTest() {
        firstVariantToAdd();
        Map<Integer, List<Employee>> expectedOrganisations = new LinkedHashMap<>();
        List<Employee> organisation1 = new ArrayList<>();
        organisation1.add(employee1Chef);
        organisation1.add(employee2);
        organisation1.add(employee3);
        organisation1.add(employee4);
        organisation1.add(employee5);

        List<Employee> organisation2 = new ArrayList<>();
        organisation2.add(employee6Chef);
        organisation2.add(employee7);
        organisation2.add(employee8);
        organisation2.add(employee9);
        organisation2.add(employee10);
        organisation2.add(employee11);

        expectedOrganisations.put(employee1Chef.getId(), organisation1);
        expectedOrganisations.put(employee6Chef.getId(), organisation2);

        //Тест 1. При первом варианте заполнения.
        ManagerReport reportFirst = new ManagerReport(employees);
        Assertions.assertEquals(expectedOrganisations, reportFirst.getOrganizations());

        //Тест 2. При втором варианте заполнения.
        secondVariantToAdd();
        Assertions.assertEquals(expectedOrganisations, reportFirst.getOrganizations());
    }

    /**
     * Тест метода, формирующего отсортированный список руководителей по количеству подчиненных и имени.
     */
    @Test
    public void getManagersRating() {
        firstVariantToAdd();
        Map<Employee, Integer> expectedRating = new LinkedHashMap<>();
        expectedRating.put(employee6Chef, 5);
        expectedRating.put(employee1Chef, 4);
        expectedRating.put(employee8, 3);
        expectedRating.put(employee9, 2);
        expectedRating.put(employee2, 2);
        expectedRating.put(employee4, 0);
        expectedRating.put(employee7, 0);
        expectedRating.put(employee3, 0);
        expectedRating.put(employee5, 0);
        expectedRating.put(employee10, 0);
        expectedRating.put(employee11, 0);

        //Тест 1. В правильном ли порядке стоят сотрудники.
        Set<Employee> actual = new ManagerReport(employees).getManagersRating().keySet();
        Set<Employee> expected = new HashSet<>(employees);
        Assertions.assertEquals(expected, actual);

        //Тест 2. При первом варианте заполнения.
        ManagerReport report = new ManagerReport(employees);
        Assertions.assertEquals(expectedRating,report.getManagersRating());

        //Тест 3 При втором варианте заполнения.
        secondVariantToAdd();
        Assertions.assertEquals(expectedRating, report.getManagersRating());
    }
}
