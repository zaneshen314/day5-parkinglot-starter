package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StrandParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void should_return_ticket_when_parking_boy_park_given_a_car() {
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        boy.workInParkingLot(parkingLot);
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_given_a_ticket() {
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        boy.workInParkingLot(parkingLot);
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
        StrandParkingBoy boy = new StrandParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        boy.workInParkingLot(parkingLot);
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

    @Test
    public void should_print_error_message_when_parking_boy_fetch_given_wrong_ticket(){
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        boy.workInParkingLot(parkingLot);
        Car car = new Car();
        Ticket ticket = new Ticket();
        // When
        Ticket ignore = boy.park(car);
        Car fetch = boy.fetch(ticket);
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    @Test
    public void should_print_error_message_when_parking_boy_fetch_given_used_ticket() {
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
        ParkingLot parkingLot = new ParkingLot();
        boy.workInParkingLot(parkingLot);
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        Car fetch = boy.fetch(ticket);
        Car usedFetch = boy.fetch(ticket);
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    @Test
    public void should_print_error_message_when_parking_boy_given_parking_lot_is_full() {
        // Given
        ParkingLot parkingLot = new ParkingLot(0);
        StrandParkingBoy boy = new StrandParkingBoy();
        boy.workInParkingLot(parkingLot);
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        // Then
        assertThat(systemOut()).contains(NO_AVAILABLE_POSITION_ERROR_MSG);
    }

    @Test
    public void should_return_park_car_in_first_parking_lot_when_parking_boy_fetch_given_parking_boy_has_two_available_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        StrandParkingBoy boy = new StrandParkingBoy();
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
    public void should_return_park_car_in_first_parking_lot_when_parking_boy_fetch_given_parking_boy_has_two_parking_lot_given_only_one_have_space() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot();
        StrandParkingBoy boy = new StrandParkingBoy();
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
    public void should_right_car_when_parking_boy_has_two_parking_lot_fetch_given_two_ticket() {
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot seconedParkingLot = new ParkingLot();
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
    public void should_print_error_when_parking_boy_has_two_parking_lot_given_error_ticket() {
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
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
    public void should_print_error_when_parking_boy_has_two_parking_lot_given_used_ticket() {
        // Given
        StrandParkingBoy boy = new StrandParkingBoy();
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
    public void should_return_null_and_print_error_parking_lot_when_parking_boy_fetch_given_parking_boy_has_two_parking_lot_given_no_one_have_space() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        StrandParkingBoy boy = new StrandParkingBoy();
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
