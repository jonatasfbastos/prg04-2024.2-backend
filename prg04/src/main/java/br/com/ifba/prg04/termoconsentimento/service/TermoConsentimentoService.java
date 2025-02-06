package br.com.ifba.prg04.termoconsentimento.service;

import br.com.ifba.prg04.infrastructure.exception.DatabaseException;
import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import br.com.ifba.prg04.termoconsentimento.repository.TermoConsentimentoRepository;
import br.com.ifba.prg04.termoconsentimento.repository.projection.TermoConsentimentoProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TermoConsentimentoService implements TermoConsentimentoIService {

    private final TermoConsentimentoRepository repository;


    @Override
    @Transactional
    public TermoConsentimento create(TermoConsentimento termoConsentimento) {
        termoConsentimento.setDataHoraConsentimento(LocalDateTime.now());

        try{
            return repository.save(termoConsentimento);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TermoConsentimento findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum termo de consentimento com o id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TermoConsentimentoProjection> findByPaciente(String paciente, Pageable pageable) {
        //Adicionar verificação de paciente quando a feature de usuários for adicionada
        return repository.findByPaciente(paciente, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TermoConsentimentoProjection> findAll(Pageable pageable) {
        return repository.findAllPageable(pageable);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TermoConsentimento termoConsentimento = findById(id);

        try{
            repository.delete(termoConsentimento);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade.");
        }
    }

}
