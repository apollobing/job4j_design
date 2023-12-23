package ru.job4j.ood.isp.violation;

public class Tesla implements Car {
    @Override
    public String drive() {
        return "Tesla drives.";
    }

    @Override
    public Boolean autoDrive() {
        return true;
    }
}
