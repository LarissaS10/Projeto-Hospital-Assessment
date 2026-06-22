package com.larissa.hospital.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ConsultaRequestDTO {
    private LocalDate dataConsulta;
    private String observacoes;
    private Long pacienteId;
    private Long medicoId;
}