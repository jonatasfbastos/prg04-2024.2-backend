package br.com.ifba.prg04.requisicao.controller;

import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;
import br.com.ifba.prg04.requisicao.service.RequisicaoIService;
import jakarta.validation.Valid;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requisicoes")
@RequiredArgsConstructor
public class RequisicaoController {

    private final RequisicaoIService requisicaoService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<RequisicaoGetResponseDto> saveRequisicao(@Valid @RequestBody RequisicaoPostRequestDto requisicaoDto) {
        var requisicao = requisicaoService.save(requisicaoDto);
        var responseDto = objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<RequisicaoGetResponseDto>> findAllRequisicoes(Pageable pageable) {
        Page<RequisicaoGetResponseDto> page = requisicaoService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisicaoGetResponseDto> findRequisicaoById(@PathVariable Long id) {
        var requisicao = requisicaoService.findById(id);
        var responseDto = objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<Page<RequisicaoGetResponseDto>> findRequisicoesByPacienteNome(@PathVariable String nome, Pageable pageable) {
        Page<RequisicaoGetResponseDto> page = requisicaoService.findByPacienteNome(nome, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscar/cpf/{cpf}")
    public ResponseEntity<Page<RequisicaoGetResponseDto>> findRequisicoesByPacienteCpf(@PathVariable String cpf, Pageable pageable) {
        Page<RequisicaoGetResponseDto> page = requisicaoService.findByPacienteCpf(cpf, pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequisicaoById(@PathVariable Long id) {
        requisicaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
