package br.com.ifba.prg04.funcionario.service;

import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.funcionario.repositories.FuncionarioRepository;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario saveFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Page<Funcionario> getAllFuncionarios(Pageable pageable) {
        return funcionarioRepository.findAll(pageable);
    }

    public Funcionario getFuncionarioById(Long id) {
        // Lançando exceção quando não encontrar o funcionário
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id));
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
    @Transactional(readOnly = true)
    public Funcionario getFuncionarioByCodigo(String codigo) {
        log.info("Buscando funcionário com CPF: {}", codigo);
        return funcionarioRepository.findByCodigo(codigo)
                .orElseThrow(() -> {
                    log.warn("Funcionário não encontrado para o CPF: {}", codigo);
                    return new ResourceNotFoundException("Não foi encontrado nenhum funcionário com o CPF: " + codigo);
                });
    }
    public void deleteFuncionario(Long id) {
        // Verificando se o funcionário existe antes de tentar deletá-lo
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id));

        funcionarioRepository.deleteById(id);
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionarioAtualizado) {
        // Busca o funcionário existente no banco de dados
        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com o ID: " + id));

        // Atualiza apenas os campos permitidos se os valores não forem nulos
    
        if (funcionarioAtualizado.getEndereco() != null) {
            funcionarioExistente.setEndereco(funcionarioAtualizado.getEndereco());
        }
        if (funcionarioAtualizado.getTelefone() != null) {
            funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        }

        // Salva as alterações no banco de dados
        return funcionarioRepository.save(funcionarioExistente);
    }
}
