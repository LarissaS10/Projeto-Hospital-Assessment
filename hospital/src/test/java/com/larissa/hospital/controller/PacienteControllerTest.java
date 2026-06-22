package com.larissa.hospital.controller;

import com.larissa.hospital.model.Paciente;
import com.larissa.hospital.repository.ConsultaRepository;
import com.larissa.hospital.repository.PacienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PacienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    private final List<Long> pacientesCriados = new ArrayList<>();

    @AfterEach
    void limpar() {
        pacientesCriados.forEach(id -> pacienteRepository.deleteById(id));
        pacientesCriados.clear();
    }

    @Test
    void deveCadastrarPacienteComSucesso() throws Exception {
        String json = """
                {
                    "nome": "Teste Integração",
                    "cpf": "999.888.777-66",
                    "dataNascimento": "1992-02-02",
                    "telefone": "(51) 90000-0000"
                }
                """;

        String responseBody = mockMvc.perform(post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Teste Integração"))
                .andExpect(jsonPath("$.cpf").value("999.888.777-66"))
                .andReturn().getResponse().getContentAsString();

        Long idCriado = extrairId(responseBody);
        pacientesCriados.add(idCriado);

        assertTrue(pacienteRepository.findById(idCriado).isPresent());
    }

    @Test
    void deveBuscarPacienteCadastrado() throws Exception {
        Paciente paciente = criarPacienteNoBanco("Paciente Buscado", "111.111.111-11");

        mockMvc.perform(get("/pacientes/" + paciente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(paciente.getId()))
                .andExpect(jsonPath("$.nome").value("Paciente Buscado"));
    }

    @Test
    void deveListarTodosOsPacientes() throws Exception {
        Paciente paciente = criarPacienteNoBanco("Paciente Listagem", "222.222.222-22");

        mockMvc.perform(get("/pacientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[?(@.id == " + paciente.getId() + ")]").exists());
    }

    @Test
    void deveExcluirPacienteComSucesso() throws Exception {
        Paciente paciente = criarPacienteNoBanco("Paciente Exclusao", "333.333.333-33");

        mockMvc.perform(delete("/pacientes/" + paciente.getId()))
                .andExpect(status().isNoContent());

        Optional<Paciente> resultado = pacienteRepository.findById(paciente.getId());
        assertTrue(resultado.isEmpty());

        pacientesCriados.clear();
    }

    private Paciente criarPacienteNoBanco(String nome, String cpf) {
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setDataNascimento(LocalDate.of(1990, 5, 20));
        paciente.setTelefone("(51) 91111-1111");
        Paciente salvo = pacienteRepository.save(paciente);
        pacientesCriados.add(salvo.getId());
        return salvo;
    }

    private Long extrairId(String json) {
        Matcher matcher = Pattern.compile("\"id\":(\\d+)").matcher(json);
        matcher.find();
        return Long.parseLong(matcher.group(1));
    }
}