package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class RegulationsTest {
    @Test
    public void whenParkTreeCarsAndTwoTrucksThenTransportUsesTreeTwoPlaces() {
        Parking regulations = new Regulations(3, 2);
        Transport car1 = new Car("0x15");
        Transport car2 = new Car("jk81");
        Transport car3 = new Car("l99e");
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        regulations.takePark(car1);
        regulations.takePark(truck1);
        regulations.takePark(car2);
        regulations.takePark(truck2);
        regulations.takePark(car3);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(3);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(2);
    }

    @Test
    public void whenParkThreeTrucksThenTransportUsesTwoTwoPlaces() {
        Parking regulations = new Regulations(2, 2);
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        Transport truck3 = new Truck("n7v3");
        regulations.takePark(truck2);
        regulations.takePark(truck1);
        regulations.takePark(truck3);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(2);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(2);
    }

    @Test
    public void whenParkTwoCarsAndThreeTrucksThenThirdTruckCanNotPark() {
        Parking regulations = new Regulations(2, 2);
        Transport car1 = new Car("0x15");
        Transport car2 = new Car("jk81");
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        Transport truck3 = new Truck("n7v3");
        regulations.takePark(car1);
        regulations.takePark(truck1);
        regulations.takePark(truck2);
        regulations.takePark(truck3);
        regulations.takePark(car2);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(2);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(2);
        assertThat(regulations.isFull()).isEqualTo(true);
    }

    @Test
    public void whenParkTwoTrucksAndThreeCarsThenThirdCarCanNotPark() {
        Parking regulations = new Regulations(2, 2);
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        Transport car1 = new Car("0x15");
        Transport car2 = new Car("jk81");
        Transport car3 = new Car("l99e");
        regulations.takePark(truck1);
        regulations.takePark(car1);
        regulations.takePark(truck2);
        regulations.takePark(car2);
        regulations.takePark(car3);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(2);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(2);
        assertThat(regulations.isFull()).isEqualTo(true);
    }

    @Test
    public void whenParkThreeTrucksAndVacateOneFromCarPlacesThenTransportUsesZeroTwoPlaces() {
        Parking regulations = new Regulations(2, 2);
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        Transport truck3 = new Truck("n7v3");
        regulations.takePark(truck1);
        regulations.takePark(truck2);
        regulations.takePark(truck3);
        regulations.vacatePark(truck3);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(0);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(2);
    }

    @Test
    public void whenParkThreeTrucksAndVacateOneFromTruckPlacesThenTransportUsesTwoOnePlaces() {
        Parking regulations = new Regulations(2, 2);
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        Transport truck3 = new Truck("n7v3");
        regulations.takePark(truck1);
        regulations.takePark(truck2);
        regulations.takePark(truck3);
        regulations.vacatePark(truck2);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(2);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(1);
    }

    @Test
    public void whenParkThreeCarsAndVacateOneThenTransportUsesTwoPlaces() {
        Parking regulations = new Regulations(3, 2);
        Transport car1 = new Car("0x15");
        Transport car2 = new Car("jk81");
        Transport car3 = new Car("l99e");
        regulations.takePark(car1);
        regulations.takePark(car2);
        regulations.takePark(car3);
        regulations.vacatePark(car2);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(2);
    }

    @Test
    public void whenParkThreeCarsAndTwoTrucksAndVacateOneOfEachThenTransportUsesTwoOnePlaces() {
        Parking regulations = new Regulations(3, 2);
        Transport car1 = new Car("0x15");
        Transport car2 = new Car("jk81");
        Transport car3 = new Car("l99e");
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        regulations.takePark(car1);
        regulations.takePark(car2);
        regulations.takePark(truck1);
        regulations.takePark(car3);
        regulations.takePark(truck2);
        regulations.vacatePark(car2);
        regulations.vacatePark(truck1);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(2);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(1);
    }

    @Test
    public void whenVacateTruckAndCarAndParkAgainThenTransportUsesThreeTwoPlaces() {
        Parking regulations = new Regulations(3, 2);
        Transport car1 = new Car("0x15");
        Transport car2 = new Car("jk81");
        Transport car3 = new Car("l99e");
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        regulations.takePark(car1);
        regulations.takePark(car2);
        regulations.takePark(truck1);
        regulations.takePark(car3);
        regulations.takePark(truck2);
        regulations.vacatePark(car2);
        regulations.vacatePark(truck1);
        regulations.takePark(car2);
        regulations.takePark(truck1);
        assertThat(regulations.getOccupiedByAny()).isEqualTo(3);
        assertThat(regulations.getOccupiedByTrucks()).isEqualTo(2);
    }

    @Test
    public void whenFindTrucksAndCarPlacesThenGetTransportPlaces() {
        Parking regulations = new Regulations(3, 2);
        Transport truck1 = new Truck("1z09");
        Transport truck2 = new Truck("b24y");
        Transport truck3 = new Truck("n7v3");
        Transport car1 = new Car("0x15");
        regulations.takePark(truck1);
        regulations.takePark(car1);
        regulations.takePark(truck2);
        regulations.takePark(truck3);
        assertThat(regulations.findOccupiedPlace(truck1))
                .isEqualTo("Truck #1z09, took place #1");
        assertThat(regulations.findOccupiedPlace(car1))
                .isEqualTo("Car #0x15, took place #1");
        assertThat(regulations.findOccupiedPlace(truck3))
                .isEqualTo("Truck #n7v3, took places #2 and #3");
    }
}