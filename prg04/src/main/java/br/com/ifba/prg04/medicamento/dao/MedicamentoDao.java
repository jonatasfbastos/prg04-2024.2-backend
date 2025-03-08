package br.com.ifba.prg04.medicamento.dao;

import br.com.ifba.prg04.medicamento.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoDao extends JpaRepository<Medicamento, Long> {
    /**
     * Procurando por nome e Categoria no repository.
     * Utilizando essa anotação para desconsiderar maiúscula e minúscula
     * */

    List<Medicamento> findByNomeContainingIgnoreCase(String Nome);
    List<Medicamento> findByCategoriaContainingIgnoreCase(String Nome);
}
