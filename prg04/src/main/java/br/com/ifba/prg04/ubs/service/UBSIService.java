package br.com.ifba.prg04.ubs.service;

import br.com.ifba.prg04.ubs.entity.UBS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UBSIService {
    Page<UBS> findAll(Pageable pageable);
    UBS findById(Long id);
    UBS save(UBS ubs);
    void delete(Long Id);
    UBS update(UBS ubs);
}
