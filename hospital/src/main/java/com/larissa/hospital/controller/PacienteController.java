package com.larissa.hospital.controller;

import com.larissa.hospital.dto.PacienteRequestDTO;
import com.larissa.hospital.model.Paciente;
import com.larissa.hospital.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente cadastrar(@RequestBody PacienteRequestDTO dto) {
        return pacienteService.cadastrar(dto);
    }

    @GetMapping("/{id}")
    public Paciente buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPorId(id);
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.listar();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        pacienteService.remover(id);
    }
}