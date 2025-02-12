package br.com.ifba.prg04.vacinacao.services;

import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.vacinacao.entities.Lote;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import br.com.ifba.prg04.vacinacao.repositories.LoteIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        if (lote.getVacina() == null ) {
            log.warn("Nenhuma vacina encontrada");
            throw new ResourceNotFoundException("Nenhuma vacina encontrada");
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
        log.info("Buscando lote com ID: " + id);
        return loteIRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum lote encontrado"));
    }

    @Override
    @Transactional
    public Page<Lote> findAllLote(Pageable pageable) {
        try {
            log.info("Buscando todos lotes");
            return loteIRepository.findAll(pageable);
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao buscar todos os lotes: " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteLoteById(Long Id) {
        log.info("Deletando vacina com ID: " + Id);
        try {
            loteIRepository.deleteById(Id);
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao deletar vacina com ID: " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Lote updateLote(Lote lote) {
        if (!this.loteIRepository.existsById(lote.getId())) {
            log.warn("Nenhum lote encontrado com esse Id: " + lote.getId());
            throw new ResourceNotFoundException("Nenhum lote encontrado com esse id: "
                    + lote.getId());
        }
        try {
            log.info("Atualizando lote com ID: " + lote.getId());
            return loteIRepository.save(lote);
        }catch (DataAccessException e){
            log.error("Erro ao atualizar lote com ID: " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
