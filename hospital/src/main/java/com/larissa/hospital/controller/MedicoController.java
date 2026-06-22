package com.larissa.hospital.controller;

import com.larissa.hospital.dto.MedicoRequestDTO;
import com.larissa.hospital.model.Medico;
import com.larissa.hospital.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medico cadastrar(@RequestBody MedicoRequestDTO dto) {
        return medicoService.cadastrar(dto);
    }

    @GetMapping
    public List<Medico> listar() {
        return medicoService.listar();
    }
}