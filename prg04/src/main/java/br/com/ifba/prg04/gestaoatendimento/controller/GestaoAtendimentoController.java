package br.com.ifba.prg04.gestaoatendimento.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoPost;
import br.com.ifba.prg04.gestaoatendimento.dto.DtoAtendimentoResponse;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;
import br.com.ifba.prg04.gestaoatendimento.mapper.GestaoAtendimentoMapper;
import br.com.ifba.prg04.gestaoatendimento.service.GestaoAtendimentoService;
import br.com.ifba.prg04.infrastructure.dto.PageableDtoGeneric;
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
    // metodo para buscar um atendimento por codigo
    @GetMapping("/findatendimento/{codigo}")
    public ResponseEntity<DtoAtendimentoResponse> findbycodigo(@PathVariable String codigo){
        GestaoAtendimento atendimento = gestaoAtendimentoService.findbycodigo(codigo);
        DtoAtendimentoResponse dtoAtendimentoResponse = GestaoAtendimentoMapper.toDtoAtendimentoResponse(atendimento);

        return ResponseEntity.ok(dtoAtendimentoResponse);
    }
    // metodo save
    @PostMapping("/save")
    public ResponseEntity<DtoAtendimentoResponse> save(@RequestBody  @Valid DtoAtendimentoPost dtoAtendimentoPost) {
      GestaoAtendimento atendimento = gestaoAtendimentoService.save(dtoAtendimentoPost);
      DtoAtendimentoResponse dtoAtendimentoResponse = GestaoAtendimentoMapper.toDtoAtendimentoResponse(atendimento); 
        
        return ResponseEntity.ok(dtoAtendimentoResponse);
       
    }

    @PutMapping("/update/{codigo}")
    public ResponseEntity<DtoAtendimentoResponse> update(@RequestBody  @Valid DtoAtendimentoPost dtoAtendimentoPost, @PathVariable String code) {
        GestaoAtendimento atendimento = gestaoAtendimentoService.update(dtoAtendimentoPost,code);
        
        DtoAtendimentoResponse dtoAtendimentoResponse = GestaoAtendimentoMapper.toDtoAtendimentoResponse(atendimento);
        return ResponseEntity.ok(dtoAtendimentoResponse);
    }
    // metodo para deletar
     @DeleteMapping("/delete/{codigo}")
     public ResponseEntity<Void> delete(@PathVariable String codigo){
        gestaoAtendimentoService.delete(codigo);
        
        return ResponseEntity.ok().build();
     }

}
