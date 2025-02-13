package br.com.ifba.prg04.prontuario.repository;

import br.com.ifba.prg04.prontuario.entity.Prontuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    @Query("SELECT p FROM Prontuario p WHERE p.paciente.id = :id")
    Page<Prontuario> findByIdPaciente(@Param("id") Long id, Pageable pageable);

    @Query("SELECT p FROM Prontuario p WHERE p.id = :id")
    Optional<Prontuario> findById(/*@Param("id")*/ Long id);


}
