package com.larissa.hospital.config;

import com.larissa.hospital.model.Medico;
import com.larissa.hospital.model.Paciente;
import com.larissa.hospital.repository.MedicoRepository;
import com.larissa.hospital.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public DataLoader(MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Medico medico1 = new Medico();
        medico1.setNome("Dr. Elias Flach");
        medico1.setCrm("123456-RS");
        medico1.setEspecialidade("Cardiologista");
        medicoRepository.save(medico1);

        Medico medico2 = new Medico();
        medico2.setNome("Dra. Larissa Souza");
        medico2.setCrm("654321-RS");
        medico2.setEspecialidade("Ortopedista");
        medicoRepository.save(medico2);

        Paciente paciente1 = new Paciente();
        paciente1.setNome("João Silva");
        paciente1.setCpf("123.456.789-00");
        paciente1.setDataNascimento(LocalDate.of(1980, 2, 18));
        paciente1.setTelefone("(51) 99999-1111");
        pacienteRepository.save(paciente1);

        Paciente paciente2 = new Paciente();
        paciente2.setNome("Maria Oliveira");
        paciente2.setCpf("987.654.321-00");
        paciente2.setDataNascimento(LocalDate.of(1975, 5, 27));
        paciente2.setTelefone("(51) 99999-2222");
        pacienteRepository.save(paciente2);
    }
}