package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportIT implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimiter;

    public ReportIT(Store store, DateTimeParser<Calendar> dateTimeParser, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimiter = delimiter;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder employees =
                new StringBuilder()
                        .append("Name").append(delimiter)
                        .append("Hired").append(delimiter)
                        .append("Fired").append(delimiter)
                        .append("Salary").append(delimiter)
                        .append(System.lineSeparator());
        store.findBy(filter)
                .stream()
                .map(emp -> emp.getName() + delimiter
                        + dateTimeParser.parse(emp.getHired()) + delimiter
                        + dateTimeParser.parse(emp.getFired()) + delimiter
                        + emp.getSalary() + delimiter + System.lineSeparator())
                .forEach(employees::append);
        return employees.toString();
    }
}
