package ru.job4j.ood.lsp.parking;

interface Parking {
    void takePark(Transport transport);

    void vacatePark(Transport transport);

    String findOccupiedPlace(Transport transport);

    int getOccupiedByAny();

    int getOccupiedByTrucks();

    boolean isFull();
}
