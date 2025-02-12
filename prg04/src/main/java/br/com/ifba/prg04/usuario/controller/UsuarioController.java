package br.com.ifba.prg04.usuario.controller;

import br.com.ifba.prg04.infrastructure.dto.PageableDto;
import br.com.ifba.prg04.infrastructure.mapper.PageableMapper;
import br.com.ifba.prg04.usuario.dto.UsuarioCreateDto;
import br.com.ifba.prg04.usuario.dto.UsuarioResponseDto;
import br.com.ifba.prg04.usuario.dto.UsuarioUpdateDto;
import br.com.ifba.prg04.usuario.dto.mapper.UsuarioMapper;
import br.com.ifba.prg04.usuario.entity.Usuario;
import br.com.ifba.prg04.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioIService service;

    @PostMapping("/create")
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario = service.create(usuario);
        return ResponseEntity.status(201).body(UsuarioMapper.toDto(usuario));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable("id") Long id,
                                                     @Valid @RequestBody UsuarioUpdateDto dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario = service.update(id, usuario);
        return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable("id") Long id) {
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(usuario));
    }

    @GetMapping
    public ResponseEntity<PageableDto> findAll(Pageable pageable) {
        Page<Usuario> page = service.findAll(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(page));
    }

    @GetMapping("/search")
    public ResponseEntity<PageableDto> searchByName(@RequestParam("nome") String nome, Pageable pageable) {
        Page<Usuario> page = service.findByNome(nome, pageable);
        return ResponseEntity.ok(PageableMapper.toDto(page));
    }
}
