package br.com.ifba.prg04.requisicao.repository;

import br.com.ifba.prg04.requisicao.entity.Requisicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
    Page<Requisicao> findByPacienteNome(String nome, Pageable pageable);
    Page<Requisicao> findByPacienteCpf(String cpf, Pageable pageable);
}
