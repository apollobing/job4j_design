package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJSONTest {
    @Test
    public void whenReportToJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String delimiter = ",";
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportJSON(store, parser);
        String expect = "[{"
                + "\"name\":\"" + worker1.getName() + "\"" + delimiter
                + "\"hired\":\"" + parser.parse(worker1.getHired()) + "\"" + delimiter
                + "\"fired\":\"" + parser.parse(worker1.getFired()) + "\"" + delimiter
                + "\"salary\":" + worker1.getSalary() + "}" + delimiter
                + " {\"name\":\"" + worker2.getName() + "\"" + delimiter
                + "\"hired\":\"" + parser.parse(worker2.getHired()) + "\"" + delimiter
                + "\"fired\":\"" + parser.parse(worker2.getFired()) + "\"" + delimiter
                + "\"salary\":" + worker2.getSalary() + "}" + delimiter
                + " {\"name\":\"" + worker3.getName() + "\"" + delimiter
                + "\"hired\":\"" + parser.parse(worker3.getHired()) + "\"" + delimiter
                + "\"fired\":\"" + parser.parse(worker3.getFired()) + "\"" + delimiter
                + "\"salary\":" + worker3.getSalary() + "}]";
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }
}