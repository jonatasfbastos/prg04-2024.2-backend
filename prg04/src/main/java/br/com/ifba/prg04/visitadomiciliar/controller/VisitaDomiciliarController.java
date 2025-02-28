package br.com.ifba.prg04.visitadomiciliar.controller;

import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarRequestDto;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarResponseDto;
import br.com.ifba.prg04.visitadomiciliar.service.VisitaDomiciliarService;
import br.com.ifba.prg04.infrastructure.mapper.PageableMapper;
import br.com.ifba.prg04.infrastructure.dto.PageableDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //controlador REST
@RequestMapping("/visitas") // Define o endpoint base para essa API
@RequiredArgsConstructor
public class VisitaDomiciliarController {

    private final VisitaDomiciliarService service;// Injeção de dependência automática

    @PostMapping
    public ResponseEntity<VisitaDomiciliarResponseDto> criar(@Valid @RequestBody VisitaDomiciliarRequestDto dto) {
        return ResponseEntity.ok(service.salvar(dto)); // Cria uma nova visita
    }

    @GetMapping
    public ResponseEntity<List<VisitaDomiciliarResponseDto>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas()); // Lista todas as visitas
    }

    @GetMapping("/paginado")
    public ResponseEntity<PageableDto> listarPaginado(Pageable pageable) {
        Page<VisitaDomiciliarResponseDto> page = service.listarTodas(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(page)); // Lista visitas com paginação
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitaDomiciliarResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id)); // Busca visita por ID
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);  // Deleta visita por ID
        return ResponseEntity.noContent().build();
    }
}

