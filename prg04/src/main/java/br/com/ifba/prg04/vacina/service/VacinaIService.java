package br.com.ifba.prg04.vacina.service;

import br.com.ifba.prg04.vacina.entity.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface VacinaIService {
    Vacina save(Vacina vacina);
    Vacina findById(Long id);
    Page<Vacina> findAll(Pageable pageable);
    void deleteById(Long id);
    Vacina updateVacina(Vacina vacina);
    Page<Vacina> findByDoencaCombatida(String doencaCombatida, Pageable pageable);
    Page<Vacina> findByDataVencimento(LocalDate dataVencimento, Pageable pageable);
}