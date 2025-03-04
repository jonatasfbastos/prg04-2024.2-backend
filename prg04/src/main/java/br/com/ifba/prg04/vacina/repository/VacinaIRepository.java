package br.com.ifba.prg04.vacina.repository;

import br.com.ifba.prg04.vacina.entity.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VacinaIRepository extends JpaRepository<Vacina, Long> {
    Page<Vacina> findByDoencaCombatida(String doencaCombatida, Pageable pageable);
    Page<Vacina> findByDataVencimento(LocalDate dataVencimento, Pageable pageable);
}