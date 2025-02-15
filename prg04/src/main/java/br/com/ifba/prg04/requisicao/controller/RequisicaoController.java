package br.com.ifba.prg04.requisicao.controller;

import br.com.ifba.prg04.requisicao.dto.RequisicaoGetResponseDto;
import br.com.ifba.prg04.requisicao.dto.RequisicaoPostRequestDto;
import br.com.ifba.prg04.requisicao.service.RequisicaoIService;
import jakarta.validation.Valid;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/requisicoes")
@RequiredArgsConstructor
public class RequisicaoController {

    private final RequisicaoIService requisicaoService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping
    public ResponseEntity<RequisicaoGetResponseDto> saveRequisicao(@Valid @RequestBody RequisicaoPostRequestDto requisicaoDto) {
        var requisicao = requisicaoService.save(requisicaoDto);
        RequisicaoGetResponseDto responseDto = objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoGetResponseDto>> findAllRequisicoes() {
        var requisicoes = requisicaoService.findAll();
        List<RequisicaoGetResponseDto> responseDtos = requisicoes.stream()
                .map(requisicao -> objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisicaoGetResponseDto> findRequisicaoById(@PathVariable Long id) {
        var requisicao = requisicaoService.findById(id);
        RequisicaoGetResponseDto responseDto = objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<List<RequisicaoGetResponseDto>> findRequisicoesByPacienteNome(@PathVariable String nome) {
        var requisicoes = requisicaoService.findByPacienteNome(nome);
        List<RequisicaoGetResponseDto> responseDtos = requisicoes.stream()
                .map(requisicao -> objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/buscar/cpf/{cpf}")
    public ResponseEntity<List<RequisicaoGetResponseDto>> findRequisicoesByPacienteCpf(@PathVariable String cpf) {
        var requisicoes = requisicaoService.findByPacienteCpf(cpf);
        List<RequisicaoGetResponseDto> responseDtos = requisicoes.stream()
                .map(requisicao -> objectMapperUtil.map(requisicao, RequisicaoGetResponseDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequisicaoById(@PathVariable Long id) {
        requisicaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}