package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final Integer DEFAULT_CAPACITY = 10;
    private Integer maxCapacity;
    private Integer currentCapacity;

    private final Map<Ticket, Car> parkingRecords = new HashMap<>();

    public ParkingLot(){
        this.maxCapacity = DEFAULT_CAPACITY;
        this.currentCapacity = 0;
    }

    public ParkingLot(Integer maxCapacity){
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
    }

    public Ticket park(Car car){
        if ((int) maxCapacity == currentCapacity) {
            return null;
        }
        Ticket ticket = new Ticket();
        parkingRecords.put(ticket, car);
        currentCapacity ++;
        return ticket;
    }

    public Car fetch(Ticket ticket){
        return parkingRecords.remove(ticket);
    }
}
