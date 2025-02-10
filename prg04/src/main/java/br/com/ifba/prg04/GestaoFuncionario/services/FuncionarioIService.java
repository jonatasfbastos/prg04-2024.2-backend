package br.com.ifba.prg04.GestaoFuncionario.services;

import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioIService {
    Funcionario save(Funcionario funcionario);
    Funcionario findById(Long id);
    Page<Funcionario> findAll(Pageable pageable);
    void deleteById(Long id);
    Funcionario updateFuncionario(Funcionario funcionario);
    Funcionario findByCpf(String cpf);
}