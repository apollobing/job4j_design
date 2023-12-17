package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private Store store;
    private JAXBContext context = JAXBContext.newInstance(Employees.class);
    private Marshaller marshaller = context.createMarshaller();
    private static DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();

    public ReportXML(Store store) throws JAXBException {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employee = store.findBy(filter);
        String rsl = "";
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        }
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employee), writer);
            rsl = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return rsl;
    }

    @XmlRootElement(name = "employees")
    public static class Employees {

        @XmlElement(name = "employee")
        List<Employees> emp;

        @XmlElement(name = "name")
        private String name;
        @XmlElement(name = "hired")
        private String hired;
        @XmlElement(name = "fired")
        private String fired;
        @XmlElement(name = "salary")
        private Double salary;

        public Employees() {
        }

        public Employees(List<Employee> employee) {
            getEmployees(employee);
        }

        public Employees(String name, String hired, String fired, double salary) {
            this.name = name;
            this.hired = hired;
            this.fired = fired;
            this.salary = salary;
        }

        public void getEmployees(List<Employee> employee) {
            emp = employee.stream()
                    .map(emp -> new Employees(emp.getName(),
                            dateTimeParser.parse(emp.getHired()),
                            dateTimeParser.parse(emp.getFired()),
                            emp.getSalary()))
                    .toList();
        }
    }
}

