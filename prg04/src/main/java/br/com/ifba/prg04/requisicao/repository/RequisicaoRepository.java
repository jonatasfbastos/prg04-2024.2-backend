package br.com.ifba.prg04.requisicao.repository;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoRepository extends JpaRepository<RequisicaoEntity, Long> {

}
