package br.com.ifba.prg04.GestaoFuncionario.repositories;

import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}