package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void should_return_park_car_in_second_parking_lot_when_super_smart_parking_boy_fetch_given_parking_boy_given_has_two_parking_lot_given_second_parking_lot_more_empty() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot(11);
        SmartParkingBoy boy = new SmartParkingBoy();
        boy.workInParkingLot(parkingLot1);
        boy.workInParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        // Then
        assertFalse(parkingLot1.existTicket(ticket));
        Car fetchedCar2 = parkingLot2.fetch(ticket);
        assertEquals(car,fetchedCar2);
    }

    @Test
    public void should_right_car_when_super_smart_parking_boy_has_two_parking_lot_fetch_given_two_ticket() {
        // Given
        SmartParkingBoy boy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot seconedParkingLot = new ParkingLot(6);
        boy.workInParkingLot(firstParkingLot);
        boy.workInParkingLot(seconedParkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();
        // When
        Ticket firstTicket = boy.park(firstCar);
        Ticket secondTicket = boy.park(secondCar);
        Car firstFetch = boy.fetch(firstTicket);
        Car secondFetch = boy.fetch(secondTicket);
        // Then
        assertEquals(firstCar, firstFetch);
        assertEquals(secondCar, secondFetch);
    }

    @Test
    public void should_print_error_when_super_smart_parking_boy_has_two_parking_lot_given_error_ticket() {
        // Given
        SmartParkingBoy boy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot seconedParkingLot = new ParkingLot();
        boy.workInParkingLot(firstParkingLot);
        boy.workInParkingLot(seconedParkingLot);
        Car firstCar = new Car();
        // When
        Ticket ticket = boy.park(firstCar);
        Car fetch = boy.fetch(new Ticket());
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    @Test
    public void should_print_error_when_super_smart_parking_boy_has_two_parking_lot_given_used_ticket() {
        // Given
        SmartParkingBoy boy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot seconedParkingLot = new ParkingLot();
        boy.workInParkingLot(firstParkingLot);
        boy.workInParkingLot(seconedParkingLot);
        Car firstCar = new Car();
        // When
        Ticket ticket = boy.park(firstCar);
        Car firstFetch = boy.fetch(ticket);
        Car secondFetch = boy.fetch(ticket);
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    @Test
    public void should_return_null_and_print_error_parking_lot_when_super_smart_parking_boy_fetch_given_parking_boy_has_two_parking_lot_given_no_one_have_space() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        SmartParkingBoy boy = new SmartParkingBoy();
        boy.workInParkingLot(parkingLot1);
        boy.workInParkingLot(parkingLot2);
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        // Then
        assertThat(systemOut()).contains(NO_AVAILABLE_POSITION_ERROR_MSG);
    }
    
    private String systemOut() {
        return outContent.toString();
    }
}
