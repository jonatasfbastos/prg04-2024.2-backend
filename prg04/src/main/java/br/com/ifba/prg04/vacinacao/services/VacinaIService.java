package br.com.ifba.prg04.vacinacao.services;

import br.com.ifba.prg04.vacinacao.entities.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface VacinaIService {
    Vacina save(Vacina vacina);
    Vacina findById(Long id);
    Page<Vacina> findAll(Pageable pageable);
    void deleteById(Long id);
    Vacina updateVacina(Vacina vacina);
    Page<Vacina> findByDoencaCombatida(String doencaCombatida, Pageable pageable);
    Page<Vacina> findByDataVencimento(LocalDate dataVencimento, Pageable pageable);
    Page<Vacina> findByDataVencimentoAfter(LocalDate dataVencimentoAfter, Pageable pageable);
}
