// este codigo é uma conversao feita pelo gemini

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    private final int id;
    private final String name;
    private final List<Employee> employees;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        if (employee.departmentId() != this.id) {
            throw new IllegalArgumentException("The Department Id of employee does not match the id of the department");
        }
        this.employees.add(employee);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
