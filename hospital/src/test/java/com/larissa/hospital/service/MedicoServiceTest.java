package com.larissa.hospital.service;

import com.larissa.hospital.dto.MedicoRequestDTO;
import com.larissa.hospital.model.Medico;
import com.larissa.hospital.repository.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    @Test
    void deveCadastrarMedicoComSucesso() {
        MedicoRequestDTO dto = new MedicoRequestDTO();
        dto.setNome("Dra. Mellany Souza Flach");
        dto.setCrm("123456-RS");
        dto.setEspecialidade("Cardiologista");

        Medico medicoSalvo = new Medico();
        medicoSalvo.setId(1L);
        medicoSalvo.setNome(dto.getNome());

        when(medicoRepository.save(any(Medico.class))).thenReturn(medicoSalvo);

        Medico resultado = medicoService.cadastrar(dto);

        assertNotNull(resultado);
        assertEquals("Dra. Mellany Souza Flach", resultado.getNome());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    }
}