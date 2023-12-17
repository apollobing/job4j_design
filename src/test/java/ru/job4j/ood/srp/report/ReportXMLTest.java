package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportXMLTest {
    @Test
    public void whenReportToXML() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportXML(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employee>\n"
                + "        <name>" + worker1.getName() + "</name>\n"
                + "        <hired>" + parser.parse(worker1.getHired()) + "</hired>\n"
                + "        <fired>" + parser.parse(worker1.getFired()) + "</fired>\n"
                + "        <salary>" + worker1.getSalary() + "</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>" + worker2.getName() + "</name>\n"
                + "        <hired>" + parser.parse(worker2.getHired()) + "</hired>\n"
                + "        <fired>" + parser.parse(worker2.getFired()) + "</fired>\n"
                + "        <salary>" + worker2.getSalary() + "</salary>\n"
                + "    </employee>\n"
                + "    <employee>\n"
                + "        <name>" + worker3.getName() + "</name>\n"
                + "        <hired>" + parser.parse(worker3.getHired()) + "</hired>\n"
                + "        <fired>" + parser.parse(worker3.getFired()) + "</fired>\n"
                + "        <salary>" + worker3.getSalary() + "</salary>\n"
                + "    </employee>\n"
                + "</employees>\n";
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }
}