package com.larissa.hospital.service;

import com.larissa.hospital.dto.MedicoRequestDTO;
import com.larissa.hospital.model.Medico;
import com.larissa.hospital.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico cadastrar(MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEspecialidade(dto.getEspecialidade());
        return medicoRepository.save(medico);
    }

    public List<Medico> listar() {
        return medicoRepository.findAll();
    }
}