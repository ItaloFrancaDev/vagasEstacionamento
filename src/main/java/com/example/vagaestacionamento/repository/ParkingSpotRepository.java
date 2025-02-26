package com.example.vagaestacionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vagaestacionamento.model.ParkingSpot;
import com.example.vagaestacionamento.model.Status;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    List<ParkingSpot> findByStatus(Status status);
}