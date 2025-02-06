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

@Service
@RequiredArgsConstructor
@Slf4j
public class TermoConsentimentoService implements TermoConsentimentoIService {

    private final TermoConsentimentoRepository repository;

    /**
     * Autor: Rafael Andrade
     * Cria um novo termo de consentimento e o salva no banco de dados.
     * @param termoConsentimento Objeto contendo os dados do termo.
     * @return TermoConsentimento salvo.
     */
    @Override
    @Transactional
    public TermoConsentimento create(TermoConsentimento termoConsentimento) {
        termoConsentimento.setDataHoraConsentimento(LocalDateTime.now());
        log.info("Criando um novo termo de consentimento...");

        try {
            TermoConsentimento termoSalvo = repository.save(termoConsentimento);
            log.info("Termo de consentimento criado com sucesso: {}", termoSalvo.getId());
            return termoSalvo;
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao salvar termo de consentimento: {}", e.getMessage());
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Autor: Rafael Andrade
     * Busca um termo de consentimento pelo ID.
     * @param id Identificador do termo.
     * @return TermoConsentimento encontrado.
     */
    @Override
    @Transactional(readOnly = true)
    public TermoConsentimento findById(Long id) {
        log.info("Buscando termo de consentimento com ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Termo de consentimento não encontrado para o ID: {}", id);
                    return new ResourceNotFoundException("Não foi encontrado nenhum termo de consentimento com o id: " + id);
                });
    }

    /**
     * Autor: Rafael Andrade
     * Busca termos de consentimento associados a um paciente.
     * @param paciente Nome do paciente.
     * @param pageable Configuração de paginação.
     * @return Página de termos de consentimento filtrados pelo paciente.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TermoConsentimentoProjection> findByPaciente(String paciente, Pageable pageable) {
        log.info("Buscando termos de consentimento para o paciente: {}", paciente);
        return repository.findByPaciente(paciente, pageable);
    }

    /**
     * Autor: Rafael Andrade
     * Retorna todos os termos de consentimento de forma paginada.
     * @param pageable Configuração de paginação.
     * @return Página de termos de consentimento.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TermoConsentimentoProjection> findAll(Pageable pageable) {
        log.info("Buscando todos os termos de consentimento de forma paginada...");
        return repository.findAllPageable(pageable);
    }

    /**
     * Autor: Rafael Andrade
     * Deleta um termo de consentimento pelo ID.
     * @param id Identificador do termo a ser deletado.
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deletando termo de consentimento com ID: {}", id);
        TermoConsentimento termoConsentimento = findById(id);

        try {
            repository.delete(termoConsentimento);
            log.info("Termo de consentimento deletado com sucesso: {}", id);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao deletar termo de consentimento: {}", e.getMessage());
            throw new DatabaseException("Violação de integridade.");
        }
    }
}

