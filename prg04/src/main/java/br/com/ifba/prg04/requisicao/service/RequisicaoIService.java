package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import java.util.List;
import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import java.util.List;

public interface RequisicaoIService {
    RequisicaoEntity findBySalvar(RequisicaoEntity requisicao);
    List<RequisicaoEntity> findByListarTodas();
    RequisicaoEntity findById(Long id);
    List<RequisicaoEntity> findByPacienteNome(String nome);
    List<RequisicaoEntity> findByPacienteCpf(String cpf);
    void findByDeletar(Long id);
}
