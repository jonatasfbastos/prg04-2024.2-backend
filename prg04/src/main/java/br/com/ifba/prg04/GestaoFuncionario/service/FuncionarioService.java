package br.com.ifba.prg04.GestaoFuncionario.service;

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
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario saveFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
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
        if (funcionarioAtualizado.getSenha() != null) {
            funcionarioExistente.setSenha(funcionarioAtualizado.getSenha());
        }
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
