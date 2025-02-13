package br.com.ifba.prg04.GestaoFuncionario.services;

import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import br.com.ifba.prg04.GestaoFuncionario.repositories.FuncionarioRepository;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class FuncionarioServices {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario saveFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public Funcionario getFuncionarioByCpf(String cpf) {
        log.info("Buscando funcionário com CPF: {}", cpf);
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> {
                    log.warn("Funcionário não encontrado para o CPF: {}", cpf);
                    return new ResourceNotFoundException("Não foi encontrado nenhum funcionário com o CPF: " + cpf);
                });
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario updateFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Transactional(readOnly = true)
    public Funcionario getFuncionarioByCodigo(String codigo) {
        return funcionarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o código: " + codigo));
    }
}