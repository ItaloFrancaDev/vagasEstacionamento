package com.example.vagaestacionamento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.vagaestacionamento.model.ParkingSpot;
import com.example.vagaestacionamento.model.Reservation;
import com.example.vagaestacionamento.model.Status;
import com.example.vagaestacionamento.repository.ParkingSpotRepository;
import com.example.vagaestacionamento.repository.ReservationRepository;
import com.example.vagaestacionamento.service.ReservationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ReservationService reservationService;

    private ParkingSpot vaga;
    private Reservation reserva;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vaga = new ParkingSpot();
        vaga.setId(1L);
        vaga.setNumero("A1");
        vaga.setTipo("Comum");
        vaga.setValorPorHora(10.0);
        vaga.setStatus(Status.DISPONIVEL);

        reserva = new Reservation();
        reserva.setParkingSpotId(vaga.getId());
        reserva.setDataInicio(LocalDateTime.now());
    }

    @Test
    void testCriarReserva() {
        when(parkingSpotRepository.findById(vaga.getId())).thenReturn(Optional.of(vaga));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reserva);

        Reservation reservaCriada = reservationService.criarReserva(vaga.getId());

        assertNotNull(reservaCriada);
        assertEquals(vaga.getId(), reservaCriada.getParkingSpotId());
        assertEquals(Status.RESERVADA, vaga.getStatus());
    }

    @Test
    void testEncerrarReserva() {
        reserva.setDataInicio(LocalDateTime.now().minusHours(2));

        when(reservationRepository.findById(reserva.getId())).thenReturn(Optional.of(reserva));
        when(parkingSpotRepository.findById(vaga.getId())).thenReturn(Optional.of(vaga));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reserva);

        Reservation reservaEncerrada = reservationService.encerrarReserva(reserva.getId());

        assertNotNull(reservaEncerrada);
        assertEquals(Status.DISPONIVEL, vaga.getStatus());
        assertEquals(20.0, reservaEncerrada.getValorTotal());
    }
}

