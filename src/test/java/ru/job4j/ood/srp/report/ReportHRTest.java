package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportHRTest {

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportHR(store);
        String expect = "Name;Salary" + System.lineSeparator()
                + worker2.getName() + ";"
                + worker2.getSalary() + System.lineSeparator()
                + worker1.getName() + ";"
                + worker1.getSalary() + System.lineSeparator()
                + worker3.getName() + ";"
                + worker3.getSalary() + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }
}