package br.com.ifba.prg04.requisicao.service;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import br.com.ifba.prg04.requisicao.repository.RequisicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;

//    public RequisicaoEntity salvar(RequisicaoEntity requisicao) {
//        return requisicaoRepository.save(requisicao);
//    }
//
//    public List<RequisicaoEntity> listarTodas() {
//        return requisicaoRepository.findAll();
//    }
//
//    public Optional<RequisicaoEntity> buscarPorId(Long id) {
//        return requisicaoRepository.findById(id);
//    }
//
//    public void excluir(Long id) {
//        requisicaoRepository.deleteById(id);
//    }
}
