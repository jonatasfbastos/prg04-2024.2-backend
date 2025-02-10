package br.com.ifba.prg04.GestaoFuncionario.repositories;

import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByCpf(String cpf);

}