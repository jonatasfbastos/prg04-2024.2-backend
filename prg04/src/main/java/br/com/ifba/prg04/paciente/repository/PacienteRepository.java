package br.com.ifba.prg04.paciente.repository;

import br.com.ifba.prg04.paciente.entity.Paciente;
import br.com.ifba.prg04.prontuario.entity.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByNomeOrCpf(String nome, String cpf);

    boolean existsById(Long id);

    Optional<Paciente> findByCpf(String cpf);
}
