package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportAccountingTest {

    @Test
    public void whenAccountingRUBToUSDGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportAccounting(store, parser, Currency.RUB, Currency.USD);
        String expect = "Name;Hired;Fired;Salary" + System.lineSeparator()
                + worker1.getName() + ";"
                + parser.parse(worker1.getHired()) + ";"
                + parser.parse(worker1.getFired()) + ";"
                + converter.convert(Currency.RUB, worker1.getSalary(), Currency.USD) + System.lineSeparator()
                + worker2.getName() + ";"
                + parser.parse(worker2.getHired()) + ";"
                + parser.parse(worker2.getFired()) + ";"
                + converter.convert(Currency.RUB, worker2.getSalary(), Currency.USD) + System.lineSeparator()
                + worker3.getName() + ";"
                + parser.parse(worker3.getHired()) + ";"
                + parser.parse(worker3.getFired()) + ";"
                + converter.convert(Currency.RUB, worker3.getSalary(), Currency.USD) + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }

    @Test
    public void whenAccountingRUBToEURGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportAccounting(store, parser, Currency.RUB, Currency.EUR);
        String expect = "Name;Hired;Fired;Salary" + System.lineSeparator()
                + worker1.getName() + ";"
                + parser.parse(worker1.getHired()) + ";"
                + parser.parse(worker1.getFired()) + ";"
                + converter.convert(Currency.RUB, worker1.getSalary(), Currency.EUR) + System.lineSeparator()
                + worker2.getName() + ";"
                + parser.parse(worker2.getHired()) + ";"
                + parser.parse(worker2.getFired()) + ";"
                + converter.convert(Currency.RUB, worker2.getSalary(), Currency.EUR) + System.lineSeparator()
                + worker3.getName() + ";"
                + parser.parse(worker3.getHired()) + ";"
                + parser.parse(worker3.getFired()) + ";"
                + converter.convert(Currency.RUB, worker3.getSalary(), Currency.EUR) + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }

    @Test
    public void whenAccountingUSDToEURGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportAccounting(store, parser, Currency.USD, Currency.EUR);
        String expect = "Name;Hired;Fired;Salary" + System.lineSeparator()
                + worker1.getName() + ";"
                + parser.parse(worker1.getHired()) + ";"
                + parser.parse(worker1.getFired()) + ";"
                + converter.convert(Currency.USD, worker1.getSalary(), Currency.EUR) + System.lineSeparator()
                + worker2.getName() + ";"
                + parser.parse(worker2.getHired()) + ";"
                + parser.parse(worker2.getFired()) + ";"
                + converter.convert(Currency.USD, worker2.getSalary(), Currency.EUR) + System.lineSeparator()
                + worker3.getName() + ";"
                + parser.parse(worker3.getHired()) + ";"
                + parser.parse(worker3.getFired()) + ";"
                + converter.convert(Currency.USD, worker3.getSalary(), Currency.EUR) + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }

    @Test
    public void whenAccountingUSDToRUBGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportAccounting(store, parser, Currency.USD, Currency.RUB);
        String expect = "Name;Hired;Fired;Salary" + System.lineSeparator()
                + worker1.getName() + ";"
                + parser.parse(worker1.getHired()) + ";"
                + parser.parse(worker1.getFired()) + ";"
                + converter.convert(Currency.USD, worker1.getSalary(), Currency.RUB) + System.lineSeparator()
                + worker2.getName() + ";"
                + parser.parse(worker2.getHired()) + ";"
                + parser.parse(worker2.getFired()) + ";"
                + converter.convert(Currency.USD, worker2.getSalary(), Currency.RUB) + System.lineSeparator()
                + worker3.getName() + ";"
                + parser.parse(worker3.getHired()) + ";"
                + parser.parse(worker3.getFired()) + ";"
                + converter.convert(Currency.USD, worker3.getSalary(), Currency.RUB) + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }

    @Test
    public void whenAccountingEURToUSDGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportAccounting(store, parser, Currency.EUR, Currency.USD);
        String expect = "Name;Hired;Fired;Salary" + System.lineSeparator()
                + worker1.getName() + ";"
                + parser.parse(worker1.getHired()) + ";"
                + parser.parse(worker1.getFired()) + ";"
                + converter.convert(Currency.EUR, worker1.getSalary(), Currency.USD) + System.lineSeparator()
                + worker2.getName() + ";"
                + parser.parse(worker2.getHired()) + ";"
                + parser.parse(worker2.getFired()) + ";"
                + converter.convert(Currency.EUR, worker2.getSalary(), Currency.USD) + System.lineSeparator()
                + worker3.getName() + ";"
                + parser.parse(worker3.getHired()) + ";"
                + parser.parse(worker3.getFired()) + ";"
                + converter.convert(Currency.EUR, worker3.getSalary(), Currency.USD) + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }

    @Test
    public void whenAccountingEURToRUBGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Peter", now, now, 250);
        Employee worker3 = new Employee("Bob", now, now, 70);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportAccounting(store, parser, Currency.EUR, Currency.RUB);
        String expect = "Name;Hired;Fired;Salary" + System.lineSeparator()
                + worker1.getName() + ";"
                + parser.parse(worker1.getHired()) + ";"
                + parser.parse(worker1.getFired()) + ";"
                + converter.convert(Currency.EUR, worker1.getSalary(), Currency.RUB) + System.lineSeparator()
                + worker2.getName() + ";"
                + parser.parse(worker2.getHired()) + ";"
                + parser.parse(worker2.getFired()) + ";"
                + converter.convert(Currency.EUR, worker2.getSalary(), Currency.RUB) + System.lineSeparator()
                + worker3.getName() + ";"
                + parser.parse(worker3.getHired()) + ";"
                + parser.parse(worker3.getFired()) + ";"
                + converter.convert(Currency.EUR, worker3.getSalary(), Currency.RUB) + System.lineSeparator();
        assertThat(engine.generate(emp -> true)).isEqualTo(expect);
    }
}