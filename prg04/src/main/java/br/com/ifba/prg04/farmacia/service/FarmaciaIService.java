package br.com.ifba.prg04.farmacia.service;

import br.com.ifba.prg04.farmacia.entity.Farmacia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmaciaIService {
    Page<Farmacia> findAll(Pageable pageable);
    Farmacia findById(Long id);
    Farmacia save(Farmacia farmacia);
    void delete(Long Id);
    Farmacia update(Farmacia farmacia);
}
