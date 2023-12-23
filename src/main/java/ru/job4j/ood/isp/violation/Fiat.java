package ru.job4j.ood.isp.violation;

public class Fiat implements Car {
    @Override
    public String drive() {
        return "Fiat drives.";
    }

    @Override
    public Boolean autoDrive() {
        throw new UnsupportedOperationException();
    }
}
