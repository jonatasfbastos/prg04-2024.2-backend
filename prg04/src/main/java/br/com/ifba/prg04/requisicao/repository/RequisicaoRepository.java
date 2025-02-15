package br.com.ifba.prg04.requisicao.repository;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequisicaoRepository extends JpaRepository<RequisicaoEntity, Long> {
    List<RequisicaoEntity> findByPacienteNome(String nome); // Busca por nome do paciente
    List<RequisicaoEntity> findByPacienteCpf(String cpf);
}
