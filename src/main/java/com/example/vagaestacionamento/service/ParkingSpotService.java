package com.example.vagaestacionamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vagaestacionamento.model.ParkingSpot;
import com.example.vagaestacionamento.model.Status;
import com.example.vagaestacionamento.repository.ParkingSpotRepository;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository repository;

    public ParkingSpot cadastrarVaga(ParkingSpot vaga) {
        vaga.setStatus(Status.DISPONIVEL); 
        return repository.save(vaga);
    }

    public List<ParkingSpot> listarVagasDisponiveis() {
        return repository.findByStatus(Status.DISPONIVEL);
    }
}
