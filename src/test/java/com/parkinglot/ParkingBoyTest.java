package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

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

    @Test
    public void should_print_error_message_when_parking_boy_fetch_given_wrong_ticket(){
        // Given
        ParkingBoy boy = new ParkingBoy();
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
        ParkingBoy boy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = boy.park(car);
        Car fetch = boy.fetch(ticket);
        Car usedFetch = boy.fetch(ticket);
        // Then
        assertThat(systemOut()).contains(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
    }

    private String systemOut() {
        return outContent.toString();
    }
}
