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

    private final VisitaDomiciliarService service; // Injeção de dependência automática

    @PostMapping // Mapeia requisições HTTP POST para este metodo
    public ResponseEntity<VisitaDomiciliarResponseDto> criar(@Valid @RequestBody VisitaDomiciliarRequestDto dto) {
        return ResponseEntity.ok(service.salvar(dto)); // Chama o serviço para salvar a visita e retorna a resposta
    }

    @GetMapping // Mapeia requisições HTTP GET para listar todas as visitas
    public ResponseEntity<List<VisitaDomiciliarResponseDto>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas()); // Retorna a lista de visitas domiciliarias
    }

    @GetMapping("/paginado") // Endpoint para listar visitas de forma paginada
    public ResponseEntity<PageableDto> listarPaginado(Pageable pageable) {
        Page<VisitaDomiciliarResponseDto> page = service.listarTodas(pageable); // Obtém a lista paginada
        return ResponseEntity.ok(PageableMapper.toDto(page)); // Converte para DTO e retorna a resposta
    }
}

