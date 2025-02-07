package br.com.ifba.prg04.GestaoFuncionario.services;


import br.com.ifba.prg04.vacinacao.entities.Vacina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuncionarioIService{
    Vacina save(Vacina vacina);
    Vacina findById(Long id);
    Page<Vacina> findAll(Pageable pageable);
    void DeleteById(Long id);
    Vacina updateVacina(Vacina vacina);
}
