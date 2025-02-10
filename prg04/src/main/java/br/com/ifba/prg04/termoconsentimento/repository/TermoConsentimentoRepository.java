package br.com.ifba.prg04.termoconsentimento.repository;

import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import br.com.ifba.prg04.termoconsentimento.repository.projection.TermoConsentimentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TermoConsentimentoRepository extends JpaRepository<TermoConsentimento, Long> {

    @Query("select t from TermoConsentimento t")
    Page<TermoConsentimentoProjection> findAllPageable(Pageable pageable);

    Page<TermoConsentimentoProjection> findByPacienteId(Long id, Pageable pageable);

}
