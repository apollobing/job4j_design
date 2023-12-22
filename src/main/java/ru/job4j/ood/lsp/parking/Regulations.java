package ru.job4j.ood.lsp.parking;

public class Regulations implements Parking {
    private int occupiedByAny;
    private int occupiedByTrucks;
    private Transport[] carPlaces;
    private Transport[] truckPlaces;
    private boolean isFull;

    public Regulations(int carPlaces, int truckPlaces) {
        if (carPlaces < 2 || truckPlaces < 2) {
            throw new IllegalArgumentException("Parking has to contain two or more places.");
        }
        this.carPlaces = new Transport[carPlaces];
        this.truckPlaces = new Transport[truckPlaces];
    }

    @Override
    public void takePark(Transport transport) {
        if (occupiedByAny + transport.getSize() <= carPlaces.length && transport.getSize() < 2) {
            carPlaces[occupiedByAny] = transport;
            occupiedByAny++;
        } else if (occupiedByTrucks + transport.getSize() - 1 <= truckPlaces.length && transport.getSize() > 1) {
            truckPlaces[occupiedByTrucks] = transport;
            occupiedByTrucks++;
        } else if (occupiedByAny + transport.getSize() <= carPlaces.length && carPlaces[occupiedByAny + 1] == null) {
            carPlaces[occupiedByAny] = transport;
            carPlaces[occupiedByAny + 1] = transport;
            occupiedByAny += 2;
        } else {
            isFull = true;
            System.out.println("Parking is full, you can't park.");
        }
    }

    @Override
    public void vacatePark(Transport transport) {
        boolean vacate = false;
        for (int i = 0; transport.getSize() < 2 && i < carPlaces.length; i++) {
            if (carPlaces[i] == transport) {
                carPlaces[i] = null;
                occupiedByAny--;
                break;
            }
        }
        for (int i = 0; !vacate && transport.getSize() > 1 && i < truckPlaces.length; i++) {
            if (truckPlaces[i] == transport) {
                truckPlaces[i] = null;
                occupiedByTrucks--;
                vacate = true;
                break;
            }
        }
        for (int i = 0; !vacate && transport.getSize() > 1 && i < carPlaces.length; i++) {
            if (carPlaces[i] == transport) {
                carPlaces[i] = null;
                carPlaces[i + 1] = null;
                occupiedByAny -= 2;
                break;
            }
        }
    }

    @Override
    public String findOccupiedPlace(Transport transport) {
        String place = "";
        boolean found = false;
        for (int i = 0; transport.getSize() < 2 && i < carPlaces.length; i++) {
            if (carPlaces[i] == transport) {
                place = "Car #" + transport.getNumber() + ", took place #" + i;
                break;
            }
        }
        for (int i = 0; !found && transport.getSize() > 1 && i < truckPlaces.length; i++) {
            if (truckPlaces[i] == transport) {
                place = "Truck #" + transport.getNumber() + ", took place #" + i;
                found = true;
                break;
            }
        }
        for (int i = 0; !found && transport.getSize() > 1 && i < carPlaces.length; i++) {
            if (carPlaces[i] == transport) {
                place = "Truck #" + transport.getNumber() + ", took places #" + i + " and #" + (i + 1);
                break;
            }
        }
        return place;
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
