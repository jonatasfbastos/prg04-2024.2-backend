package br.com.ifba.prg04.atendimento.controller;

import br.com.ifba.prg04.atendimento.dto.AtendimentoDTO;
import br.com.ifba.prg04.atendimento.entity.Atendimento;
import br.com.ifba.prg04.atendimento.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {
    private final AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<List<AtendimentoDTO>> listarTodos() {
        return ResponseEntity.ok(atendimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> buscarPorId(@PathVariable Long id) {
        Optional<AtendimentoDTO> atendimento = atendimentoService.buscarPorId(id);
        return atendimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Atendimento> salvar(@RequestBody Atendimento atendimento) {
        return ResponseEntity.ok(atendimentoService.salvar(atendimento));
    }
}

