package br.com.ifba.prg04.medicamento.dao;

import br.com.ifba.prg04.medicamento.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoDao extends JpaRepository<Medicamento, Long> {

}
