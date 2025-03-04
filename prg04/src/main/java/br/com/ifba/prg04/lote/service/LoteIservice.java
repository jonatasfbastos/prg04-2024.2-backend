package br.com.ifba.prg04.lote.service;

import br.com.ifba.prg04.lote.entity.Lote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoteIservice {
    Lote saveLote(Lote lote);
    Lote findLoteById(Long id);
    Page<Lote> findAllLote(Pageable pageable);
    void deleteLoteById(Long id);
    Lote updateLote(Lote lote);

}
