package com.larissa.hospital.service;

import com.larissa.hospital.dto.ConsultaRequestDTO;
import com.larissa.hospital.dto.RankingMedicoDTO;
import com.larissa.hospital.model.Consulta;
import com.larissa.hospital.model.Medico;
import com.larissa.hospital.model.Paciente;
import com.larissa.hospital.repository.ConsultaRepository;
import com.larissa.hospital.repository.MedicoRepository;
import com.larissa.hospital.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository,
                           PacienteRepository pacienteRepository,
                           MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public Consulta cadastrar(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado!"));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado!"));

        Consulta consulta = new Consulta();
        consulta.setDataConsulta(dto.getDataConsulta());
        consulta.setObservacoes(dto.getObservacoes());
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);

        return consultaRepository.save(consulta);
    }

    public List<RankingMedicoDTO> rankingMedicos() {
        return consultaRepository.rankingMedicos();
    }
}