package com.parkinglot;

import java.util.Comparator;
import java.util.Optional;

public class SuperSmartParkingBoy extends StrandParkingBoy{
    @Override
    public Optional<ParkingLot> getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::haveSpace).max(Comparator.comparingInt(ParkingLot::getEmptyRate));
    }
}
