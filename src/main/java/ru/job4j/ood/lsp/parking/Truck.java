package ru.job4j.ood.lsp.parking;

public class Truck implements Transport {
    private static final int SIZE = 2;

    private String number;

    public Truck(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int getSize() {
        return SIZE;
    }
}
