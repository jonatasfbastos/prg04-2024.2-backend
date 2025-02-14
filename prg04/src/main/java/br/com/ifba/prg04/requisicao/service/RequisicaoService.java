package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import br.com.ifba.prg04.requisicao.repository.RequisicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisicaoService implements RequisicaoIService {

    private final RequisicaoRepository requisicaoRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    @Override
    @Transactional
    public RequisicaoEntity findBySalvar(RequisicaoEntity requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    @Override
    public List<RequisicaoEntity> findByListarTodas() {
        return requisicaoRepository.findAll();
    }

    @Override
    public RequisicaoEntity findById(Long id) {
        return requisicaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada!"));
    }

    @Override
    public List<RequisicaoEntity> findByPacienteNome(String nome) {
        return requisicaoRepository.findByPacienteNome(nome);
    }

    @Override
    public List<RequisicaoEntity> findByPacienteCpf(String cpf) {
        return requisicaoRepository.findByPacienteCpf(cpf);
    }

    @Override
    @Transactional
    public void findByDeletar(Long id) {
        if (!requisicaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Requisição não encontrada!");
        }
        requisicaoRepository.deleteById(id);
    }
}
