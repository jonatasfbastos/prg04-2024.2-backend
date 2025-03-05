package br.com.ifba.prg04.familia.service;

import br.com.ifba.prg04.familia.entity.Familia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FamiliaIService {

   Familia save(Familia familia);
   Familia update(Long id, Familia familia);
   Optional<Familia> findById(Long id);
   Page<Familia> findAll(Pageable pageable);
   List<Familia> findByName(String name);
}
