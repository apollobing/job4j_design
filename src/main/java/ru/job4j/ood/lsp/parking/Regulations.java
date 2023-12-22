package ru.job4j.ood.lsp.parking;

public class Regulations implements Parking {
    private int occupiedByAny;
    private int occupiedByTrucks;
    private Transport[] carPlaces;
    private Transport[] truckPlaces;
    private boolean isFull;

    public Regulations(int carPlaces, int truckPlaces) {
        if (carPlaces < 2 || truckPlaces < 2) {
            throw new IllegalArgumentException("Parking has to contain at least two places.");
        }
        this.carPlaces = new Transport[carPlaces];
        this.truckPlaces = new Transport[truckPlaces];
    }

    @Override
    public void takePark(Transport transport) {

    }

    @Override
    public void vacatePark(Transport transport) {

    }

    @Override
    public String findOccupiedPlace(Transport transport) {
        return "";
    }

    @Override
    public int getOccupiedByAny() {
        return occupiedByAny;
    }

    @Override
    public int getOccupiedByTrucks() {
        return occupiedByTrucks;
    }

    @Override
    public boolean isFull() {
        return isFull;
    }
}
