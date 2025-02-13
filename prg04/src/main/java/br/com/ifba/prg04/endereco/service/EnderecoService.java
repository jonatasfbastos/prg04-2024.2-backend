
package br.com.ifba.prg04.endereco.service;

import br.com.ifba.prg04.endereco.entity.Endereco;
import br.com.ifba.prg04.endereco.entity.EnderecoId;
import br.com.ifba.prg04.endereco.repository.EnderecoIRepository;
import java.util.List;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnderecoService implements EnderecoIService{
    private final EnderecoIRepository enderecoRepository; // Injeta o repositório de enderecos.
//CRUD ALUNOS
    @Override
    @Transactional
    public Endereco save(Endereco endereco){
            return enderecoRepository.save(endereco); // Salva o endereco no repositório.
    }

    @Override
    @Transactional
    public Endereco update(Endereco endereco){
            return enderecoRepository.save(endereco); // Atualiza o endereco no repositório.
    }

    @Override
    @Transactional
    public void delete(Endereco endereco){
        enderecoRepository.delete(endereco); // Deleta o endereco do repositório.
    }

    @Override
    public Endereco findById(EnderecoId id){
        return enderecoRepository.findById(id).orElseThrow(() -> new BusinessException("Endereço de id " + id + " não encontrado")); // Busca o endereco no repositório pelo ID.
    }

    @Override
    public List<Endereco> findAll(){
        return enderecoRepository.findAll(); // Busca todos os enderecos no repositório.
    }
}
