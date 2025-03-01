package br.com.ifba.prg04.upa.service;

import br.com.ifba.prg04.farmacia.entity.Farmacia;
import br.com.ifba.prg04.upa.entity.UPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UPAIService {
    Page<UPA> findAll(Pageable pageable);
    UPA findById(Long id);
    UPA save(UPA upa);
    void delete(Long Id);
    UPA update(UPA upa);
}
