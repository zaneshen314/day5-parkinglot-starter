package com.parkinglot.entity;

import com.parkinglot.strategy.ParkingStrategy;
import com.parkinglot.strategy.StrandParkingStrategy;

import java.util.ArrayList;
import java.util.List;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;

public class ParkingBoy {
    protected final List<ParkingLot> parkingLots = new ArrayList<>();

    private ParkingStrategy parkingStrategy;

    public ParkingBoy() {
        this.parkingStrategy = new StrandParkingStrategy();
    }

    public ParkingBoy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void workInParkingLot(ParkingLot lot) {
        parkingLots.add(lot);
    }
    public Ticket park(Car car) {
        return parkingStrategy.getParkingLotBySpecificOrder(parkingLots)
                .map(lot -> lot.park(car))
                .orElseGet(()->{
                    System.out.println(NO_AVAILABLE_POSITION_ERROR_MSG);;
                    return null;
                });

    }

    public Car fetch(Ticket ticket) {
        return  parkingLots.stream()
                .filter(parkingLot -> parkingLot.existTicket(ticket))
                .findFirst()
                .map(lot -> lot.fetch(ticket))
                .orElseGet(() -> {
                    System.out.println(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);
                    return null;
                });
    }
}
