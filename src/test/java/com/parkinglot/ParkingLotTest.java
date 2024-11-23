package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_parking_given_parking_lot_and_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_fetching_given_parking_lot_and_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //when
        Car fetchedCar = parkingLot.fetch(ticket);
        //then
        assertEquals(car,fetchedCar);
    }

    @Test
    public void should_return_right_car_when_fetch_given_two_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Car car2 = new Car();
        Ticket ticket = parkingLot.park(car);
        Ticket ticket2 = parkingLot.park(car2);
        //when
        Car fetchedCar = parkingLot.fetch(ticket);
        Car fetchedCar2 = parkingLot.fetch(ticket2);
        //then
        assertEquals(car,fetchedCar);
        assertEquals(car2,fetchedCar2);
    }
}
