import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", name, department, salary);
    }
}

public class MultiSortExample {

    public List<Employee> createTestData() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 90000),
                new Employee("Bob", "HR", 60000),
                new Employee("Charlie", "IT", 95000),
                new Employee("David", "HR", 60000),
                new Employee("Eve", "Sales", 80000));
        return employees;
    }

    public void sort1(List<Employee> employees) {
        // 1. Define the Comparator
        Comparator<Employee> compareByDeptAndSalary = Comparator
                .comparing(Employee::getDepartment) // Primary sort
                .thenComparing(Employee::getSalary, Comparator.reverseOrder()); // Secondary sort (Desc)

        // 2. Sort the list
        employees.sort(compareByDeptAndSalary);

        // Print results
        employees.forEach(System.out::println);
    }

    public void sortV2(List<Employee> employees) {
        Comparator<Employee> compareByDeptAndSalary = Comparator.comparing(Employee::getDepartment)
                .thenComparing(Employee::getSalary, Comparator.reverseOrder());

        employees.sort(compareByDeptAndSalary);
        employees.forEach(System.out::println);

    }

    public void sortV3(List<Employee> employees) {
        Comparator<Employee> compareDeptAndSalary = Comparator.comparing(Employee::getDepartment).thenComparing(Employee::getSalary, Comparator.reverseOrder());

        employees.sort(compareDeptAndSalary);
        employees.forEach(System.out::println);
    }

    public void test() {
        List<Employee> employees = createTestData();
        sort1(employees);
        System.out.println("===================");

        employees = createTestData();
        sortV2(employees);
        System.out.println("===================");

        employees = createTestData();
        sortV3(employees);
        System.out.println("===================");
    }

    public static void main(String[] args) {
        // List<Employee> employees = Arrays.asList(
        // new Employee("Alice", "IT", 90000),
        // new Employee("Bob", "HR", 60000),
        // new Employee("Charlie", "IT", 95000),
        // new Employee("David", "HR", 60000),
        // new Employee("Eve", "Sales", 80000));

        // // 1. Define the Comparator
        // Comparator<Employee> compareByDeptAndSalary = Comparator
        // .comparing(Employee::getDepartment) // Primary sort
        // .thenComparing(Employee::getSalary, Comparator.reverseOrder()); // Secondary
        // sort (Desc)

        // // 2. Sort the list
        // employees.sort(compareByDeptAndSalary);

        // // Print results
        // employees.forEach(System.out::println);

        MultiSortExample util = new MultiSortExample();
        util.test();
    }
}