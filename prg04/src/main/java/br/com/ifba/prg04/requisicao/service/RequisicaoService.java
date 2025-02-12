package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import br.com.ifba.prg04.requisicao.repository.RequisicaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    @Transactional
    public RequisicaoEntity salvar(RequisicaoEntity requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    public List<RequisicaoEntity> listarTodas() {
        return requisicaoRepository.findAll();
    }

    public RequisicaoEntity buscarPorId(Long id) {
        return requisicaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Requisição não encontrada!"));
    }

    @Transactional
    public void deletar(Long id) {
        if (!requisicaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Requisição não encontrada!");
        }
        requisicaoRepository.deleteById(id);
    }
}
