package br.com.ifba.prg04.familia.service;

import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.repository.FamiliaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //logger
public class FamiliaService implements FamiliaIService {

    @Autowired
    private final FamiliaRepository familiaRepository;

    @Override
    public Familia save( @Valid Familia familia) {
        log.info("Salvando familia: {}", familia.getNome());
        Familia familiaSalva = familiaRepository.save(familia);
        log.info("Familia salva com sucesso!");
        return familiaSalva;
    }

    @Override
    @Transactional
    public Familia update(Long id, Familia familia) {
        //busca familia existente no banco
        Familia familiaAtual = familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Familia nao encontrada."));

        //verifica se a familia nao eh nula
        if (familia == null) {
            throw new RuntimeException("Dados da familia nao podem ser nulos");
        }

        familiaAtual.setNome(familia.getNome());
        familiaAtual.setMembros(familia.getMembros());
        familiaAtual.setEndereco(familia.getEndereco());
        familiaAtual.setResponsavel(familia.getResponsavel());

        log.info("Atualizando familia: {}", familiaAtual.getNome());
        Familia familiaAtualizada = familiaRepository.save(familiaAtual);
        log.info("Familia atualizada com sucesso!");
        return familiaAtualizada;
    }

    @Override
    public Optional<Familia> findById(Long id) {
        return familiaRepository.findById(id);
    }

    @Override
    public Page<Familia> findAll(Pageable pageable) {
        return familiaRepository.findAll(pageable);
    }

    public List<Familia> findByName(String nome) {
        return familiaRepository.findByNome(nome);
    }

}
