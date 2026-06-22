package com.larissa.hospital.controller;

import com.larissa.hospital.dto.ConsultaRequestDTO;
import com.larissa.hospital.dto.RankingMedicoDTO;
import com.larissa.hospital.model.Consulta;
import com.larissa.hospital.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta cadastrar(@RequestBody ConsultaRequestDTO dto) {
        return consultaService.cadastrar(dto);
    }

    @GetMapping("/ranking-medicos")
    public List<RankingMedicoDTO> rankingMedicos() {
        return consultaService.rankingMedicos();
    }
}