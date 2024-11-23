package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {

    @Test
    public void should_return_ticket_when_parking_boy_park_given_a_car() {
        // Given
        ParkingBoy boy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_given_a_ticket() {
        // Given
        ParkingBoy boy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        Car fetchCar = boy.fetch(ticket);
        // Then
        assertEquals(car, fetchCar);
    }
    @Test
    public void should_return_right_car_when_parking_boy_fetch_given_two_ticket() {
        //given
        ParkingBoy boy = new ParkingBoy();
        Car car = new Car();
        Car car2 = new Car();
        Ticket ticket = boy.park(car);
        Ticket ticket2 = boy.park(car2);
        //when
        Car fetchedCar = boy.fetch(ticket);
        Car fetchedCar2 = boy.fetch(ticket2);
        //then
        assertEquals(car,fetchedCar);
        assertEquals(car2,fetchedCar2);
    }
}
