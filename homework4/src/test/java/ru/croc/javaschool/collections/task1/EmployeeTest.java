package ru.croc.javaschool.collections.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тест для {@link Employee}.
 */
public class EmployeeTest {
    /**
     * Сотрудники.
     */
    private Employee
            employee1,
            employee2,
            employee3;

    /**
     * Начальная инициализация сотрудников.
     */
    @BeforeEach
    public void init() {
        employee1 = new Employee(1, "Aaa");
        employee2 = new Employee(2, "Baa");
        employee3 = new Employee(3, "Baa");
    }

    /**
     * Тест метода, сравнивающего сотрудников по именам. Если имена одинаковые, сравниваются уникальные
     * идентификаторы.
     */
    @Test
    public void compareToTest() {
        int result1 = employee1.compareTo(employee2);
        int result2 = employee2.compareTo(employee3);
        int result3 = employee3.compareTo(employee1);
        Assertions.assertEquals(-1, result1);
        Assertions.assertEquals(-1, result2);
        Assertions.assertEquals(1, result3);
    }
}

