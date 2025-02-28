package br.com.ifba.prg04.hospital.service;

import br.com.ifba.prg04.farmacia.entity.Farmacia;
import br.com.ifba.prg04.hospital.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalIService {
    Page<Hospital> findAll(Pageable pageable);
    Hospital findById(Long id);
    Hospital save(Hospital hospital);
    void delete(Long Id);
    Hospital update(Hospital hospital);
}
