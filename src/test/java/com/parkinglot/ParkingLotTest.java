package com.parkinglot;

import com.parkinglot.entity.Car;
import com.parkinglot.entity.ParkingLot;
import com.parkinglot.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

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

    @Test
    public void should_return_nothing_when_fetch_given_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //when
        Car fetchedCar = parkingLot.fetch(new Ticket());
        //then
        assertNull(fetchedCar);
    }

    @Test
    public void should_return_nothing_when_fetch_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        //when
        Car fetchedCar = parkingLot.fetch(ticket);
        //then
        assertNull(fetchedCar);
    }

    @Test
    public void should_return_nothing_when_parking_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        //when
        Ticket ticket = parkingLot.park(car);
        //then
        assertNull(ticket);
    }

    @Test
    public void should_print_error_message_when_fetch_given_wrong_ticket(){

        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = new Ticket();
        // When
        Ticket ignore = parkingLot.park(car);
        Car fetch = parkingLot.fetch(ticket);
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    @Test
    public void should_print_error_message_when_fetch_given_used_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        Car fetch = parkingLot.fetch(ticket);
        Car usedFetch = parkingLot.fetch(ticket);
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    @Test
    public void should_print_error_message_when_given_parking_lot_is_full() {
        // Given
        ParkingLot parkingLot = new ParkingLot(0);
        Car car = new Car();
        // When
        Ticket ticket = parkingLot.park(car);
        // Then
        assertThat(systemOut()).contains(NO_AVAILABLE_POSITION_ERROR_MSG);
    }

    private String systemOut() {
        return outContent.toString();
    }
}
