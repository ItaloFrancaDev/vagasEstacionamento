package com.example.vagaestacionamento;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.vagaestacionamento.model.ParkingSpot;
import com.example.vagaestacionamento.model.Status;
import com.example.vagaestacionamento.repository.ParkingSpotRepository;
import com.example.vagaestacionamento.service.ParkingSpotService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

public class ParkingSpotServiceTest {

    @Mock
    private ParkingSpotRepository parkingSpotRepository;

    @InjectMocks
    private ParkingSpotService parkingSpotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarVaga() {
        ParkingSpot vaga = new ParkingSpot();
        vaga.setNumero("A1");
        vaga.setTipo("Comum");
        vaga.setValorPorHora(10.0);
        vaga.setStatus(Status.DISPONIVEL);

        when(parkingSpotRepository.save(vaga)).thenReturn(vaga);

        ParkingSpot vagaCadastrada = parkingSpotService.cadastrarVaga(vaga);

        assertNotNull(vagaCadastrada);
        assertEquals("A1", vagaCadastrada.getNumero());
        assertEquals(Status.DISPONIVEL, vagaCadastrada.getStatus());
    }

    @Test
    void testListarVagasDisponiveis() {
        ParkingSpot vaga1 = new ParkingSpot();
        vaga1.setStatus(Status.DISPONIVEL);

        ParkingSpot vaga2 = new ParkingSpot();
        vaga2.setStatus(Status.OCUPADA);

        when(parkingSpotRepository.findByStatus(Status.DISPONIVEL)).thenReturn(Arrays.asList(vaga1));

        List<ParkingSpot> vagasDisponiveis = parkingSpotService.listarVagasDisponiveis();

        assertNotNull(vagasDisponiveis);
        assertEquals(1, vagasDisponiveis.size());
        assertEquals(Status.DISPONIVEL, vagasDisponiveis.get(0).getStatus());
    }
}

