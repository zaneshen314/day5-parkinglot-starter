package com.parkinglot;

public class ParkingBoy {
    private final ParkingLot parkingLot = new ParkingLot();
    public Ticket park(Car car) {
        return parkingLot.park(car);
    }
}
