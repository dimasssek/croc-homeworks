package ru.croc.javaschool.collections.task1;

/**
 * Сотрудник.
 */
public class Employee implements Comparable<Employee> {
    /**
     * Уникальный идентификатор.
     */
    private final Integer id;
    /**
     * Имя сотрудника.
     */
    private final String name;
    /**
     * Руководитель.
     */
    private Employee manager;

    /**
     * Создаёт {@link Employee}. Руководитель.
     *
     * @param id   уникальный идентификатор.
     * @param name имя сотрудника.
     */
    public Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.manager = null;
    }

    /**
     * Создаёт {@link Employee}. Работяга.
     *
     * @param id   уникальный идентификатор.
     * @param name имя сотрудника.
     */
    public Employee(Integer id, String name, Employee manager) {
        this(id, name);
        this.manager = manager;
    }

    /**
     * Возвращает уникальный идентификатор.
     *
     * @return уникальный идентификатор.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает имя сотрудника.
     *
     * @return имя сотрудника.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает руководителя.
     *
     * @return руководитель.
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * Реализация метода интерфейса {@link Comparable}. Сравнение двух сотрудников по имени.
     * Если имена одинаковы, сравнение идет по id.
     *
     * @param employee сотрудник.
     * @return результат сравнения.
     */
    @Override
    public int compareTo(Employee employee) {
        int result = this.getName().compareToIgnoreCase(employee.getName());
        return result != 0 ? (result > 0 ? 1 : -1) : Integer.compare(this.getId(), employee.getId());
    }

    /**
     * Представление информации о сотруднике в строке.
     * @return информация о сотруднике.
     */
    @Override
    public String toString() {
        if (manager != null) {
            return "\nСотрудник с номером " + id + " по имени " +
                    name + " находится в подчинении: \n" + "Руководитель c номером " +
                    manager.getId() + " по имени " + manager.getName();
        }
        return "\nРуководитель c номером " + id + " по имени " + name;
    }
}
