package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.parkinglot.constant.ErrorConstant.NO_AVAILABLE_POSITION_ERROR_MSG;
import static com.parkinglot.constant.ErrorConstant.UNRECOGNIZED_PARKING_TICKET_ERROR_MSG;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy() {
    }

    public void workInParkingLot(ParkingLot lot) {
        parkingLots.add(lot);
    }
    public Ticket park(Car car) {
        return getAvailableParkingLot()
                .map(lot -> lot.park(car))
                .orElseGet(()->{
                    System.out.println(NO_AVAILABLE_POSITION_ERROR_MSG);;
                    return null;
                });

    }

    public Car fetch(Ticket ticket) {
        return getAvailableParkingLot()
                .map(lot -> lot.fetch(ticket))
                .orElseGet(()->{
                    System.out.println(UNRECOGNIZED_PARKING_TICKET_ERROR_MSG);;
                    return null;
                });
    }

    public Optional<ParkingLot> getAvailableParkingLot() {
        return parkingLots.stream().filter(ParkingLot::haveSpace).findFirst();
    }
}
