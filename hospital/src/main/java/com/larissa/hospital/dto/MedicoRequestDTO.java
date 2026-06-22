package com.larissa.hospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicoRequestDTO {
    private String nome;
    private String crm;
    private String especialidade;
}