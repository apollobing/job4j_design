package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder employees =
                new StringBuilder().append("Name;Salary").append(System.lineSeparator());
        store.findBy(filter)
                .stream().sorted((emp1, emp2) -> (int) (emp2.getSalary() - emp1.getSalary()))
                .map(emp -> emp.getName() + ";" + emp.getSalary() + System.lineSeparator())
                .forEach(employees::append);
        return employees.toString();
    }
}
