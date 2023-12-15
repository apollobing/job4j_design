package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccounting implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Currency source;
    private final Currency target;

    public ReportAccounting(Store store, DateTimeParser<Calendar> dateTimeParser, Currency source, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        StringBuilder employees =
                new StringBuilder().append("Name;Hired;Fired;Salary").append(System.lineSeparator());
        store.findBy(filter)
                .stream()
                .map(emp -> emp.getName() + ";"
                        + dateTimeParser.parse(emp.getHired()) + ";"
                        + dateTimeParser.parse(emp.getFired()) + ";"
                        + converter.convert(source, emp.getSalary(), target) + System.lineSeparator())
                .forEach(employees::append);
        return employees.toString();
    }
}
