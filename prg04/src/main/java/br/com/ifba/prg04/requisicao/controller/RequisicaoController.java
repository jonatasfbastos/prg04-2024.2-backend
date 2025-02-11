package br.com.ifba.prg04.requisicao.controller;
import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import br.com.ifba.prg04.requisicao.service.RequisicaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    private final RequisicaoService requisicaoService;

    public RequisicaoController(RequisicaoService requisicaoService) {
        this.requisicaoService = requisicaoService;
    }

    @PostMapping
    public ResponseEntity<RequisicaoEntity> criarRequisicao(@Valid @RequestBody RequisicaoEntity requisicao) {
        return ResponseEntity.ok(requisicaoService.salvar(requisicao));
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoEntity>> listarRequisicoes() {
        return ResponseEntity.ok(requisicaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisicaoEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(requisicaoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRequisicao(@PathVariable Long id) {
        requisicaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
