package ru.job4j.ood.isp.violation;

public class Swan implements Bird {
    @Override
    public String swim() {
        return "Swan swims.";
    }

    @Override
    public String fly() {
        return "Swan flies.";
    }
}
