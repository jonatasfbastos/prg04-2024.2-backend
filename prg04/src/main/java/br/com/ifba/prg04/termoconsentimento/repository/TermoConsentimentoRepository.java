package br.com.ifba.prg04.termoconsentimento.repository;

import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import br.com.ifba.prg04.termoconsentimento.repository.projection.TermoConsentimentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TermoConsentimentoRepository extends JpaRepository<TermoConsentimento, Long> {

    @Query("select t from TermoConsentimento t")
    Page<TermoConsentimentoProjection> findAllPageable(Pageable pageable);

    Page<TermoConsentimentoProjection> findByPacienteId(Long id, Pageable pageable);

    @Query("SELECT t FROM TermoConsentimento t WHERE t.paciente.cpf LIKE CONCAT(:cpfPrefix, '%')")
    Page<TermoConsentimentoProjection> findByPacienteCpfLike(@Param("cpfPrefix") String cpfPrefix, Pageable pageable);


}
