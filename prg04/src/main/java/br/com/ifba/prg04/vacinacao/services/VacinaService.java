package br.com.ifba.prg04.vacinacao.services;

import br.com.ifba.prg04.infrastructure.exception.BusinessException;
import br.com.ifba.prg04.infrastructure.exception.DatabaseException;
import br.com.ifba.prg04.infrastructure.exception.ErrorMessage;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.vacinacao.entities.Vacina;
import br.com.ifba.prg04.vacinacao.repositories.VacinaIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacinaService implements VacinaIService{
    private final VacinaIRepository vacinaIRepository;

    @Override
    @Transactional
    public Vacina save(Vacina vacina) {
        log.info("Salvando nova vacina");

        try {
            Vacina vacinaCriada = vacinaIRepository.save(vacina);
            log.info("Vacina salva com sucesso");
            return vacinaCriada;
        }catch (DataAccessException e){
            log.error("Erro ao salvar vacina: " + e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Vacina findById(Long id) {
        log.info("Buscando vacina com ID: " + id);
        return vacinaIRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma vacina encontrada"));
    }

    @Override
    @Transactional
    public Page<Vacina> findAll(Pageable pageable) {
        try {
            log.info("Buscando todas vacinas");
            return vacinaIRepository.findAll(pageable);
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao buscar todas vacinas: " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deletando vacina com ID: " + id);
        try {
            vacinaIRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao deletar vacina com ID: " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Vacina updateVacina(Vacina vacina) {
        if(!this.vacinaIRepository.existsById(vacina.getId())) {
            log.warn("Nenhuma vacina encontrada com esse Id: " + vacina.getId());
            throw new ResourceNotFoundException("Nenhuma vacina encontrada com esse id: "
                    + vacina.getId());
        }

        try {
            log.info("Atualizando vacina com ID: " + vacina.getId());
            return vacinaIRepository.save(vacina);
        }catch (DataAccessException e){
            log.error("Erro ao atualizar vacina com ID: " + e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Vacina> findByDoencaCombatida(String doencaCombatida, Pageable pageable){
        try {
            log.info("Filtrando vacinas por doenca: {}", doencaCombatida);
            Page<Vacina> vacinas = vacinaIRepository.findByDoencaCombatida(doencaCombatida, pageable);
            return vacinas;
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao filtrar vacinas por doenca");
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Vacina> findByDataVencimento(LocalDate dataVencimento, Pageable pageable){
        try {
            log.info("Filtrando vacinas por data de vencimento: {}", dataVencimento);
            Page<Vacina> vacinas = vacinaIRepository.findByDataVencimento(dataVencimento, pageable);
            return vacinas;
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao filtrar vacinas por data de vencimento");
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}