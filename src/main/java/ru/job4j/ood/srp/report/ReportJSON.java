package ru.job4j.ood.srp.report;

import com.google.gson.JsonObject;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJSON implements Report {
    private Store store;
    private DateTimeParser<Calendar> dateTimeParser;
    private JsonObject jsonObject;

    public ReportJSON(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        List<JsonObject> json = new ArrayList<>();
        for (Employee employee : employees) {
            jsonObject = new JsonObject();
            jsonObject.addProperty("name", employee.getName());
            jsonObject.addProperty("hired", dateTimeParser.parse(employee.getHired()));
            jsonObject.addProperty("fired", dateTimeParser.parse(employee.getFired()));
            jsonObject.addProperty("salary", employee.getSalary());
            json.add(jsonObject);
        }
        return json.toString();
    }
}
