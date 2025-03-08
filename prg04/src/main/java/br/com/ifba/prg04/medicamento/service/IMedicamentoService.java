package br.com.ifba.prg04.medicamento.service;


import br.com.ifba.prg04.medicamento.entity.Medicamento;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMedicamentoService {
    Page<Medicamento> findAll(Pageable pageable);
    Optional<Medicamento> findById(Long id);
    List<Medicamento> findByNome(String nome);
    List<Medicamento> findByCategoria(String nome);
    Medicamento save(Medicamento medicamento);
    Medicamento update(Long id, Medicamento medicamento);
    void deleteById(Long id);

}
