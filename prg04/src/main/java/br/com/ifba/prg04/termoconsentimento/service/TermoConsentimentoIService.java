package br.com.ifba.prg04.termoconsentimento.service;

import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import br.com.ifba.prg04.termoconsentimento.repository.projection.TermoConsentimentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TermoConsentimentoIService {

    TermoConsentimento create(TermoConsentimento termoConsentimento, String cpfPaciente, String cpfFuncionario);
    TermoConsentimento findById(Long id);
    Page<TermoConsentimentoProjection> findByIdPaciente(Long idPaciente, Pageable pageable);
    Page<TermoConsentimentoProjection> findByCpfPaciente(String cpf, Pageable pageable);
    Page<TermoConsentimentoProjection> findAll(Pageable pageable);
    void delete(Long id);

}
