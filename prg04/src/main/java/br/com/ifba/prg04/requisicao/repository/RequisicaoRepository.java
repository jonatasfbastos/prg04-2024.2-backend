package br.com.ifba.prg04.requisicao.repository;

import br.com.ifba.prg04.requisicao.entity.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    List<Requisicao> findByPacienteNome(String nome); // Busca por nome do paciente
    List<Requisicao> findByPacienteCpf(String cpf);
}
