package br.com.ifba.prg04.funcionario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ifba.prg04.funcionario.entities.Funcionario;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByCodigo(String codigo);

}