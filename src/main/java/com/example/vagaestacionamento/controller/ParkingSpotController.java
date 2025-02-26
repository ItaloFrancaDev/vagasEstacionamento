package com.example.vagaestacionamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vagaestacionamento.model.ParkingSpot;
import com.example.vagaestacionamento.service.ParkingSpotService;

@RestController
@RequestMapping("/api/vagas")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping
    public ResponseEntity<ParkingSpot> cadastrarVaga(@RequestBody ParkingSpot vaga) {
        return ResponseEntity.ok(parkingSpotService.cadastrarVaga(vaga));
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ParkingSpot>> listarVagasDisponiveis() {
        return ResponseEntity.ok(parkingSpotService.listarVagasDisponiveis());
    }
}
