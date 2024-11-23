package com.parkinglot.strategy;

import com.parkinglot.entity.ParkingLot;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    Optional<ParkingLot> getParkingLotBySpecificOrder(List<ParkingLot> parkingLots);
}
