package com.larissa.hospital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "internacoes")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEntrada;
    private LocalDate dataAlta;
    private String quarto;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}