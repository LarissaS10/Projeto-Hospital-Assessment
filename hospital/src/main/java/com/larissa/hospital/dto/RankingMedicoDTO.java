package com.larissa.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RankingMedicoDTO {
    private Long medicoId;
    private String nomeMedico;
    private Long totalConsultas;
}