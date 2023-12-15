package ru.job4j.ood.srp;

public class CarEngineRepair {
    private final String model;
    private final int vEngine;

    public CarEngineRepair(String model, int vEngine) {
        this.model = model;
        this.vEngine = vEngine;
    }

    public void repair() {
        System.out.println(model + " V" + vEngine + " engine has been fixed.");
    }

    public void paint() {
        System.out.println(model + " has been painted.");
    }

    public static void main(String[] args) {
        CarEngineRepair car = new CarEngineRepair("BMW X6", 8);
        car.repair();
        car.paint();
    }
}
