package com.parkinglot.strategy;

import com.parkinglot.entity.ParkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartParkingStrategy implements ParkingStrategy {
    public Optional<ParkingLot> getParkingLotBySpecificOrder(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(ParkingLot::haveSpace)
                .max(Comparator.comparingInt(ParkingLot::getAvailablePosition));
    }
}
