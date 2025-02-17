package br.com.ifba.prg04.agenda.repository;

import br.com.ifba.prg04.agenda.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    //Query para verificar se há conflito de horário
    @Query("SELECT COUNT(a) > 0 FROM Agenda a WHERE " +
            "(a.dataHoraInicio < :dataHoraFim AND a.dataHoraFim > :dataHoraInicio) " +
            "AND a.cancelado = false")
    boolean existsByDataHoraConflito(@Param("dataHoraInicio") LocalDateTime dataHoraInicio,
                                     @Param("dataHoraFim") LocalDateTime dataHoraFim);

}
