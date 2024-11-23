package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SuperSmartParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    public void should_return_park_car_in_first_parking_lot_when_super_smart_parking_boy_fetch_given_parking_boy_given_has_two_parking_lot_given_same_number_empty() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        SmartParkingBoy boy = new SmartParkingBoy();
        boy.workInParkingLot(parkingLot1);
        boy.workInParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        // Then
        Car fetchedCar1 = parkingLot1.fetch(ticket);
        assertEquals(car,fetchedCar1);
        assertFalse(parkingLot2.existTicket(ticket));
    }

    private String systemOut() {
        return outContent.toString();
    }
}
