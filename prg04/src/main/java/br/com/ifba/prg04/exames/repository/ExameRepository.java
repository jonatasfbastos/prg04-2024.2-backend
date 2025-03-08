package br.com.ifba.prg04.exames.repository;

import br.com.ifba.prg04.exames.entity.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {
    List<Exame> findByDescricao(String descricao);
}