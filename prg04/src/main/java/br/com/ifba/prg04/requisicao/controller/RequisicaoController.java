package br.com.ifba.prg04.requisicao.controller;

import br.com.ifba.prg04.requisicao.entity.RequisicaoEntity;
import br.com.ifba.prg04.requisicao.service.RequisicaoIService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    private final RequisicaoIService requisicaoService;

    public RequisicaoController(RequisicaoIService requisicaoService) {
        this.requisicaoService = requisicaoService;
    }

    @PostMapping
    public ResponseEntity<RequisicaoEntity> findByCriarRequisicao(@Valid @RequestBody RequisicaoEntity requisicao) {
        return ResponseEntity.ok(requisicaoService.findBySalvar(requisicao));
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoEntity>> findByListarRequisicoes() {
        return ResponseEntity.ok(requisicaoService.findByListarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisicaoEntity> findByBuscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(requisicaoService.findById(id));
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<List<RequisicaoEntity>> findByBuscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(requisicaoService.findByPacienteNome(nome));
    }

    @GetMapping("/buscar/cpf/{cpf}")
    public ResponseEntity<List<RequisicaoEntity>> findByBuscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(requisicaoService.findByPacienteCpf(cpf));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> findByDeletarRequisicao(@PathVariable Long id) {
        requisicaoService.findByDeletar(id);
        return ResponseEntity.noContent().build();
    }
}
