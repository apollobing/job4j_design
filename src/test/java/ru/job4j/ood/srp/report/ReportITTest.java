package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportITTest {

    @Test
    public void whenITSemicolonGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String delimiter = ";";
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportIT(store, parser, delimiter);
        String expect = "Name;Hired;Fired;Salary;" + System.lineSeparator()
                + worker1.getName() + delimiter
                + parser.parse(worker1.getHired()) + delimiter
                + parser.parse(worker1.getFired()) + delimiter
                + worker1.getSalary() + delimiter + System.lineSeparator()
                + worker2.getName() + delimiter
                + parser.parse(worker2.getHired()) + delimiter
                + parser.parse(worker2.getFired()) + delimiter
                + worker2.getSalary() + delimiter + System.lineSeparator()
                + worker3.getName() + delimiter
                + parser.parse(worker3.getHired()) + delimiter
                + parser.parse(worker3.getFired()) + delimiter
                + worker3.getSalary() + delimiter + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }
    @Test
    public void whenITTabGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        String delimiter = "\t";
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportIT(store, parser, delimiter);
        String expect = "Name\tHired\tFired\tSalary\t" + System.lineSeparator()
                + worker1.getName() + delimiter
                + parser.parse(worker1.getHired()) + delimiter
                + parser.parse(worker1.getFired()) + delimiter
                + worker1.getSalary() + delimiter + System.lineSeparator()
                + worker2.getName() + delimiter
                + parser.parse(worker2.getHired()) + delimiter
                + parser.parse(worker2.getFired()) + delimiter
                + worker2.getSalary() + delimiter + System.lineSeparator()
                + worker3.getName() + delimiter
                + parser.parse(worker3.getHired()) + delimiter
                + parser.parse(worker3.getFired()) + delimiter
                + worker3.getSalary() + delimiter + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }
}