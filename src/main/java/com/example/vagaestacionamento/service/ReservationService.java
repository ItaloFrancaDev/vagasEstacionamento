package com.example.vagaestacionamento.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vagaestacionamento.model.ParkingSpot;
import com.example.vagaestacionamento.model.Reservation;
import com.example.vagaestacionamento.model.Status;
import com.example.vagaestacionamento.repository.ParkingSpotRepository;
import com.example.vagaestacionamento.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public Reservation criarReserva(Long vagaId) {
        Optional<ParkingSpot> vaga = parkingSpotRepository.findById(vagaId);
        if (vaga.isPresent() && vaga.get().getStatus() == Status.DISPONIVEL) {
            ParkingSpot parkingSpot = vaga.get();
            parkingSpot.setStatus(Status.RESERVADA);
            parkingSpotRepository.save(parkingSpot);

            Reservation reservation = new Reservation();
            reservation.setParkingSpotId(parkingSpot.getId());
            reservation.setDataInicio(LocalDateTime.now());

            return reservationRepository.save(reservation);
        }
        return null; 
    }

    public Reservation encerrarReserva(Long reservaId) {
        Optional<Reservation> reserva = reservationRepository.findById(reservaId);
        if (reserva.isPresent()) {
            Reservation reservation = reserva.get();
            LocalDateTime dataFim = LocalDateTime.now();
            reservation.setDataFim(dataFim);

            long horas = Duration.between(reservation.getDataInicio(), dataFim).toHours();

            long tempoMinimo = 1; 
            if (horas < tempoMinimo) {               
                throw new IllegalArgumentException("O tempo de locação é inferior ao mínimo permitido.");
            }
           
            ParkingSpot parkingSpot = parkingSpotRepository.findById(reservation.getParkingSpotId()).orElseThrow(null);
            parkingSpot.setStatus(Status.DISPONIVEL);
            parkingSpotRepository.save(parkingSpot);
            
            ParkingSpot vaga = parkingSpotRepository.findById(parkingSpot.getId()).orElseThrow(null);
            double valorTotal = vaga.getValorPorHora() * horas;
            reservation.setValorTotal(valorTotal);

            return reservationRepository.save(reservation);
        }
        return null;
    }
}
