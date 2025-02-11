package br.com.ifba.prg04.visitadomiciliar.controller;

import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarRequestDto;
import br.com.ifba.prg04.visitadomiciliar.dto.VisitaDomiciliarResponseDto;
import br.com.ifba.prg04.visitadomiciliar.service.VisitaDomiciliarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitas")
public class VisitaDomiciliarController {
    private final VisitaDomiciliarService service;

    public VisitaDomiciliarController(VisitaDomiciliarService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VisitaDomiciliarResponseDto> criar(@Valid @RequestBody VisitaDomiciliarRequestDto dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VisitaDomiciliarResponseDto>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }
}
