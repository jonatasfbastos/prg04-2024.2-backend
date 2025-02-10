package br.com.ifba.prg04.vacinacao.repositories;

import br.com.ifba.prg04.vacinacao.entities.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VacinaIRepository extends JpaRepository<Vacina, Long> {
    Page<Vacina> findByDoencaCombatida(String doencaCombatida, Pageable pageable);
    Page<Vacina> findByDataVencimento(LocalDate dataVencimento, Pageable pageable);
}