// esse codigo e uma conversao do gemini


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static record DeptEmpPair(Department department, Employee employee) {}

    public static void main(String[] args) {
        List<Department> sample1 = convertRecords(
                new String[]{
                        "57,Vendas,8032,Meire Silva,8000.0,57",
                        "32,Estoque,4368,Dom Dias,7000.0,32",
                        "57,Vendas,3298,Pedro Neto,8500.0,57",
                        "57,Vendas,8639,Carol Souza,9000.0,57",
                        "18,Marketing,6421,Davi Souto,7500.0,18",
                        "32,Estoque,7523,Lara Matos,8000.0,32",
                        "18,Marketing,2732,Bob Costa,6500.0,18"
                }
        );
        List<Department> sample2 = convertRecords(
                new String[]{
                        "57,Vendas,8032,Meire Silva,8000.0,57",
                        "18,Marketing,6421,Davi Souto,7500.0,18",
                        "18,Marketing,2732,Bob Costa,6500.0,18"
                }
        );

        sample1.forEach(department -> {
            System.out.println(department.getName() + ": ");
            department.getEmployees().forEach(employee -> {
                System.out.println("\t" + employee);
            });
        });

        System.out.println();

        sample2.forEach(department -> {
            System.out.println(department.getName() + ": ");
            department.getEmployees().forEach(employee -> {
                System.out.println("\t" + employee);
            });
        });
    }

    public static List<Department> convertRecords(String[] records) {
        List<DeptEmpPair> employeesAndDepartments = Arrays.stream(records)
                .map(item -> {
                    String[] parts = item.split(",");
                    Department dept = new Department(
                            Integer.parseInt(parts[0]),
                            parts[1]
                    );
                    Employee emp = new Employee(
                            Integer.parseInt(parts[2]),
                            parts[3],
                            Double.parseDouble(parts[4]),
                            Integer.parseInt(parts[5])
                    );
                    return new DeptEmpPair(dept, emp);
                })
                .toList();

        Map<Department, List<Employee>> departmentWithAllEmployees = employeesAndDepartments.stream()
                .collect(Collectors.groupingBy(
                        DeptEmpPair::department,
                        Collectors.mapping(DeptEmpPair::employee, Collectors.toList())
                ));
        return departmentWithAllEmployees.entrySet().stream()
                .map(entry -> {
                    Department department = entry.getKey();
                    List<Employee> employees = entry.getValue();
                    employees.forEach(department::addEmployee);
                    return department;
                })
                .collect(Collectors.toList());
    }
}
