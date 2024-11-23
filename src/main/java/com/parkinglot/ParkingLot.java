package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;

public class ParkingLot {

    private static final Integer DEFAULT_CAPACITY = 10;
    private Integer maxCapacity;


    private final Map<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(){
        this.maxCapacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(Integer maxCapacity){
        this.maxCapacity = maxCapacity;
    }

    public Ticket park(Car car){
        if ((int) maxCapacity == parkingRecords.size()) {
            System.out.println(NO_AVAILABLE_POSITION_ERROR_MSG);
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket){
        Car removed = parkingRecords.remove(ticket);
        if (removed == null) {
            System.out.println(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
        }
        return removed;
    }
}
