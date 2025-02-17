package br.com.ifba.prg04.gestaoatendimento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoPost;
import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoResponse;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.gestaoatendimento.service.GestaoAtendimentoService;
import br.com.ifba.prg04.infrastructure.dto.PageableDtoGeneric;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.prg04.usuario.entity.Usuario;
import br.com.ifba.prg04.usuario.repository.UsuarioRepository;
import br.com.ifba.prg04.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/gestaoatendimento")
@CrossOrigin("*")
@RequiredArgsConstructor
public class GestaoAtendimentoController {
    private final GestaoAtendimentoService gestaoAtendimentoService;
    private final ObjectMapperUtil mapper;// classe mapeamento

    @GetMapping("/findall")
    public ResponseEntity<PageableDtoGeneric<DtoAtendimentoResponse>> findAll(Pageable pageable) {
        // Obtenha a página original com os dados da entidade GestaoAtendimento
        Page<GestaoAtendimento> page = gestaoAtendimentoService.findall(pageable);
    
        // Mapeie cada item da página para o tipo DtoAtendimentoResponse
        Page<DtoAtendimentoResponse> pageDto = page.map(gestaoAtendimento -> mapper.map(gestaoAtendimento, DtoAtendimentoResponse.class));
    
        // Crie o PageableDtoGeneric a partir da página mapeada
        PageableDtoGeneric<DtoAtendimentoResponse> pageableDto = PageableDtoGeneric.fromPage(pageDto);
    
        // Retorne a resposta com a lista paginada
        return ResponseEntity.ok(pageableDto);
    }
    

    // metodo para buscar por codigo
    @GetMapping("/findatendimento/{code}")
    public ResponseEntity<DtoAtendimentoResponse> findbycode(@PathVariable String code){
        GestaoAtendimento atendimento = gestaoAtendimentoService.findbycode(code);
        DtoAtendimentoResponse dtoAtendimento = mapper.map(atendimento, DtoAtendimentoResponse.class);

        return ResponseEntity.ok(dtoAtendimento);
    }
    @PostMapping("/save")
    public ResponseEntity<DtoAtendimentoResponse> save(@RequestBody  @Valid DtoAtendimentoPost body) {

      GestaoAtendimento atendimento = gestaoAtendimentoService.save(body);
        
        return ResponseEntity.ok(mapper.map(atendimento, DtoAtendimentoResponse.class));
       
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<DtoAtendimentoResponse> update(@RequestBody  @Valid DtoAtendimentoPost body, @PathVariable String code) {
        GestaoAtendimento atendimento = gestaoAtendimentoService.update(body,code);
        
        return ResponseEntity.ok(mapper.map(atendimento, DtoAtendimentoResponse.class));
    }
     @DeleteMapping("/delete/{code}")
     public ResponseEntity<Void> delete(@PathVariable String code){
        gestaoAtendimentoService.delete(code);
        return ResponseEntity.ok().build();
     }

}
