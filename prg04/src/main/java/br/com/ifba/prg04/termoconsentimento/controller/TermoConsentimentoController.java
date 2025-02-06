package br.com.ifba.prg04.termoconsentimento.controller;

import br.com.ifba.prg04.infrastructure.dto.PageableDto;
import br.com.ifba.prg04.infrastructure.mapper.PageableMapper;
import br.com.ifba.prg04.termoconsentimento.dto.TermoConsentimentoCreateDto;
import br.com.ifba.prg04.termoconsentimento.dto.TermoConsentimentoResponseDto;
import br.com.ifba.prg04.termoconsentimento.dto.mapper.TermoConsentimentoMapper;
import br.com.ifba.prg04.termoconsentimento.entity.TermoConsentimento;
import br.com.ifba.prg04.termoconsentimento.repository.projection.TermoConsentimentoProjection;
import br.com.ifba.prg04.termoconsentimento.service.TermoConsentimentoIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/termo-consentimento")
@RequiredArgsConstructor
public class TermoConsentimentoController {

    private final TermoConsentimentoIService service;

    @PostMapping(value = "/create")
    public ResponseEntity<TermoConsentimentoResponseDto> create(@Valid @RequestBody TermoConsentimentoCreateDto dto) {
        TermoConsentimento termo = TermoConsentimentoMapper.toEntity(dto);
        termo = service.create(termo);
        return ResponseEntity.status(201).body(TermoConsentimentoMapper.toDto(termo));
    }

    @GetMapping(value = "/find-by-id", params = "id")
    public ResponseEntity<TermoConsentimentoResponseDto> findById(@RequestParam("id") Long id) {
        TermoConsentimento termo = service.findById(id);
        return ResponseEntity.ok(TermoConsentimentoMapper.toDto(termo));
    }

    @GetMapping(value = "/find-by-paciente", params = "paciente")
    public ResponseEntity<PageableDto> findByPaciente(@RequestParam("paciente") String paciente, Pageable pageable) {
        Page<TermoConsentimentoProjection> page = service.findByPaciente(paciente, pageable);
        return ResponseEntity.ok(PageableMapper.toDto(page));
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<PageableDto> findAll(Pageable pageable) {
        Page<TermoConsentimentoProjection> page = service.findAll(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(page));
    }

    @DeleteMapping(value = "/delete", params = "id")
    public ResponseEntity<Void> delete(@RequestParam("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
