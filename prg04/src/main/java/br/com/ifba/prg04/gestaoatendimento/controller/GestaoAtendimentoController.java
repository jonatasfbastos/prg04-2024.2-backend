package br.com.ifba.prg04.gestaoatendimento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoPost;
import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoResponse;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.gestaoatendimento.mapper.GestaoAtendimentoMapper;
import br.com.ifba.prg04.gestaoatendimento.service.GestaoAtendimentoService;
import br.com.ifba.prg04.infrastructure.dto.PageableDtoGeneric;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

   @GetMapping("/findall")
public ResponseEntity<PageableDtoGeneric<DtoAtendimentoResponse>> findAll(Pageable pageable) {
    // Obtenha a página original com os dados da entidade GestaoAtendimento
    Page<GestaoAtendimento> page = gestaoAtendimentoService.findall(pageable);

    // Mapeie cada item da página para o tipo DtoAtendimentoResponse usando o mapper estático
    Page<DtoAtendimentoResponse> pageDto = GestaoAtendimentoMapper.toDtoAtendimentoResponsePage(page);

    // Crie o PageableDtoGeneric a partir da página mapeada
    PageableDtoGeneric<DtoAtendimentoResponse> pageableDto = PageableDtoGeneric.fromPage(pageDto);

    // Retornando a resposta com a lista paginada
    return ResponseEntity.ok(pageableDto);
}
@GetMapping("/findatendimentonome/{nomeUsuario}")
public ResponseEntity<List<DtoAtendimentoResponse>> findAtendimentosByNome(@PathVariable String nomeUsuario) {
    // Buscar a lista de atendimentos com base no nome do usuário
    List<GestaoAtendimento> atendimentos = gestaoAtendimentoService.findAtendimentos(nomeUsuario);
    
    // Usando o método estático do mapper para mapear a lista de GestaoAtendimento para DtoAtendimentoResponse
    List<DtoAtendimentoResponse> listDto = GestaoAtendimentoMapper.toDtoAtendimentoResponseList(atendimentos);
    
    // Retornar a lista mapeada
    return ResponseEntity.ok(listDto);
}


    // metodo para buscar um atendimento por codigo
    @GetMapping("/findatendimento/{code}")
    public ResponseEntity<DtoAtendimentoResponse> findbycode(@PathVariable String code){
        GestaoAtendimento atendimento = gestaoAtendimentoService.findbycode(code);
        DtoAtendimentoResponse dtoAtendimento = GestaoAtendimentoMapper.toDtoAtendimentoResponse(atendimento);

        return ResponseEntity.ok(dtoAtendimento);
    }
    // metodo save
    @PostMapping("/save")
    public ResponseEntity<DtoAtendimentoResponse> save(@RequestBody  @Valid DtoAtendimentoPost body) {
      GestaoAtendimento atendimento = gestaoAtendimentoService.save(body);
      DtoAtendimentoResponse dtoAtendimento = GestaoAtendimentoMapper.toDtoAtendimentoResponse(atendimento); 
        
        return ResponseEntity.ok(dtoAtendimento);
       
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<DtoAtendimentoResponse> update(@RequestBody  @Valid DtoAtendimentoPost body, @PathVariable String code) {
        GestaoAtendimento atendimento = gestaoAtendimentoService.update(body,code);
        
        DtoAtendimentoResponse dtoAtendimento = GestaoAtendimentoMapper.toDtoAtendimentoResponse(atendimento);
        return ResponseEntity.ok(dtoAtendimento);
    }
    // metodo para deletar
     @DeleteMapping("/delete/{code}")
     public ResponseEntity<Void> delete(@PathVariable String code){
        gestaoAtendimentoService.delete(code);
        
        return ResponseEntity.ok().build();
     }

}
