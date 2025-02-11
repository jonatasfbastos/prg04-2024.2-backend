package br.com.ifba.prg04.vacinacao.services;

import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.vacinacao.entities.Lote;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import br.com.ifba.prg04.vacinacao.repositories.LoteIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoteService implements LoteIservice{
    private final LoteIRepository loteIRepository;

    @Override
    @Transactional
    public Lote saveLote(Lote lote) {
        log.info("Salvando lote");
        if (lote.getVacina() == null || lote.getVacina().getId() == null) {
            log.warn("Nenhuma vacina encontrada com esse Id");
            throw new ResourceNotFoundException("Nenhuma vacina encontrada com esse id");
        }
        try {
            log.info("Salvando lote com ID: " + lote.getId());
            return loteIRepository.save(lote);
        }catch (DataAccessException e){
            log.error("Erro ao criar lote. " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Lote findLoteById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public Page<Lote> findAllLote(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public void deleteLote(Lote lote) {

    }

    @Override
    @Transactional
    public Lote updateLote(Lote lote) {
        return null;
    }

    @Override
    public Lote findByVacina(Vacina vacina) {
        return null;
    }
}
