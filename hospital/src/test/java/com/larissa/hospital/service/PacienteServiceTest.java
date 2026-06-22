package com.larissa.hospital.service;

import com.larissa.hospital.dto.PacienteRequestDTO;
import com.larissa.hospital.model.Paciente;
import com.larissa.hospital.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void deveCadastrarPacienteComSucesso() {
        PacienteRequestDTO dto = new PacienteRequestDTO();
        dto.setNome("Apolo Souza Flach");
        dto.setCpf("123.456.789-00");
        dto.setDataNascimento(LocalDate.of(1990, 1, 1));
        dto.setTelefone("(51) 99999-0000");

        Paciente pacienteSalvo = new Paciente();
        pacienteSalvo.setId(1L);
        pacienteSalvo.setNome(dto.getNome());

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteSalvo);

        Paciente resultado = pacienteService.cadastrar(dto);

        assertNotNull(resultado);
        assertEquals("Apolo Souza Flach", resultado.getNome());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void deveBuscarPacientePorIdComSucesso() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Perola Souza Flach");

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente resultado = pacienteService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Perola Souza Flach", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> pacienteService.buscarPorId(99L)
        );

        assertTrue(exception.getMessage().contains("Paciente não encontrado"));
    }

    @Test
    void deveRemoverPacienteComSucesso() {
        when(pacienteRepository.existsById(1L)).thenReturn(true);

        pacienteService.remover(1L);

        verify(pacienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoRemoverPacienteInexistente() {
        when(pacienteRepository.existsById(99L)).thenReturn(false);

        assertThrows(
                ResponseStatusException.class,
                () -> pacienteService.remover(99L)
        );

        verify(pacienteRepository, never()).deleteById(any());
    }
}