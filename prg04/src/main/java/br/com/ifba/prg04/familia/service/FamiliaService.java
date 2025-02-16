package br.com.ifba.prg04.familia.service;

import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.repository.FamiliaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j //logger
public class FamiliaService implements FamiliaIService {

    private final FamiliaRepository familiaRepository;

    @Override
    public Familia save( @Valid Familia familia) {
        log.info("Salvando familia: {}", familia.getNome());
        Familia familiaSalva = familiaRepository.save(familia);
        log.info("Familia salva com sucesso!");
        return familiaSalva;
    }

    @Override
    public Familia update(Long id, Familia familia) {
        Familia familiaAtual = familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Familia nao encontrada."));

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
    public Familia findById(Long id) {
        return familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Familia com o id " + id + "nao encontrada"));
    }

    @Override
    public Page<Familia> findAll(Pageable pageable) {
        return familiaRepository.findAll(pageable);
    }
}
