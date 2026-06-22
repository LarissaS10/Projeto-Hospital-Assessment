package com.larissa.hospital.repository;

import com.larissa.hospital.model.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternacaoRepository extends JpaRepository<Internacao, Long> {
}