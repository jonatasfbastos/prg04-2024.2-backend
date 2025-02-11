package br.com.ifba.prg04.vacinacao.services;

import br.com.ifba.prg04.vacinacao.entities.Lote;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoteIservice {
    Lote saveLote(Lote lote);
    Lote findLoteById(Long id);
    Page<Lote> findAllLote(Pageable pageable);
    void deleteLote(Lote lote);
    Lote updateLote(Lote lote);
    Lote findByVacina(Vacina vacina);
}
