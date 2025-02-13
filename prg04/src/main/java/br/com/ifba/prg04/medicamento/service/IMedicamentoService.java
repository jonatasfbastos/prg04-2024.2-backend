package br.com.ifba.prg04.medicamento.service;


import br.com.ifba.prg04.medicamento.entity.Medicamento;

import java.util.List;
import java.util.Optional;

public interface IMedicamentoService {
    List<Medicamento> findAll();
    Optional<Medicamento> findById(Long id);
    Medicamento save(Medicamento medicamento);
    Medicamento update(Long id, Medicamento medicamento);
    void deleteById(Long id);

}
