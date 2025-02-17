package br.com.ifba.prg04.familia.repository;

import br.com.ifba.prg04.familia.entity.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Long> {

    public List<Familia> findByNome(String nome);
}
