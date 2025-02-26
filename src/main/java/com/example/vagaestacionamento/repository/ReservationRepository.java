package com.example.vagaestacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vagaestacionamento.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
