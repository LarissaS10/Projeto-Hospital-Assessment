package com.larissa.hospital.repository;

import com.larissa.hospital.dto.RankingMedicoDTO;
import com.larissa.hospital.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT new com.larissa.hospital.dto.RankingMedicoDTO(m.id, m.nome, COUNT(c)) " +
            "FROM Medico m " +
            "LEFT JOIN Consulta c ON c.medico = m " +
            "GROUP BY m.id, m.nome " +
            "ORDER BY COUNT(c) DESC")
    List<RankingMedicoDTO> rankingMedicos();
}