package ru.job4j.ood.isp.violation;

public class Eagle implements Bird {
    @Override
    public String swim() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String fly() {
        return "Eagle flies.";
    }
}
