package ru.job4j.ood.dip;

import java.util.ArrayList;

public class DIPViolation {

    public class Car {
        private String carName;
        private CarStore carStore;

        public Car(String carName, CarStore carStore) {
            this.carName = carName;
            this.carStore = carStore;
        }

        public Car addNewCar(Car car) {
            if (!carStore.store.contains(car)) {
                carStore.store.add(car);
            }
            return car;
        }
    }

    public class CarStore {
        ArrayList<Car> store = new ArrayList<>();
    }

    public class RentCar {
        private ArrayList<String> availableCars = new ArrayList<>();

        public boolean checkAvailable(String carName) {
            boolean rsl = false;
            if (availableCars.contains(carName)) {
                System.out.println("Car " + carName + " is available.");
                rsl = true;
            }
            return rsl;
        }
    }

}
