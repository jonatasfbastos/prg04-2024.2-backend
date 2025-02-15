package br.com.ifba.prg04.gestaofuncionario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ifba.prg04.gestaofuncionario.entities.Funcionario;

public interface FuncionarioIService {
    Funcionario update(Long id, Funcionario funcionario);
    Funcionario save(Funcionario funcionario);
    Funcionario findById(Long id);
    Page<Funcionario> findAll(Pageable pageable);
    void deleteById(Long id);
    Funcionario updateFuncionario(Funcionario funcionario);
    Funcionario findByCpf(String cpf);
    Funcionario getFuncionarioByCodigo(String codigo);
}