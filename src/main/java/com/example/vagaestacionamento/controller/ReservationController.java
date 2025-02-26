package com.example.vagaestacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vagaestacionamento.model.Reservation;
import com.example.vagaestacionamento.service.ReservationService;

@RestController
@RequestMapping("/api/reservas")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{vagaId}")
    public ResponseEntity<Reservation> criarReserva(@PathVariable Long vagaId) {
        Reservation reserva = reservationService.criarReserva(vagaId);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{reservaId}/encerrar")
    public ResponseEntity<Reservation> encerrarReserva(@PathVariable Long reservaId) {
        Reservation reserva = reservationService.encerrarReserva(reservaId);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        }
        return ResponseEntity.notFound().build();
    }
}