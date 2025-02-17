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
import org.springframework.web.bind.annotation.RequestParam;



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
    // rota para buscar agendamento no nome do usuario
    @GetMapping("/findatendimentonome/{nome}")
    public ResponseEntity<List<DtoAtendimentoResponse>> findatendimentosbynome(@PathVariable String nome){
        List<GestaoAtendimento> atendimentos = gestaoAtendimentoService.findAtendimentos(nome);
        List<DtoAtendimentoResponse> listDto = mapper.mapAll(atendimentos, DtoAtendimentoResponse.class);

        return ResponseEntity.ok(listDto);
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
        DtoAtendimentoPost post = new DtoAtendimentoPost();
        post.setCode(body.getCode());
        post.setDataHora(body.getDataHora());
        post.setEspecialidadeMedica(body.getEspecialidadeMedica());
        post.setUsuarioNome(body.getUsuarioNome());

      GestaoAtendimento atendimento = gestaoAtendimentoService.save(post);
        
        return ResponseEntity.ok(mapper.map(atendimento, DtoAtendimentoResponse.class));
       
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<DtoAtendimentoResponse> update(@RequestBody  @Valid DtoAtendimentoPost body, @PathVariable String code) {
        DtoAtendimentoPost post = new DtoAtendimentoPost();
        post.setCode(body.getCode());
        post.setDataHora(body.getDataHora());
        post.setEspecialidadeMedica(body.getEspecialidadeMedica());
        post.setUsuarioNome(body.getUsuarioNome());
        GestaoAtendimento atendimento = gestaoAtendimentoService.update(post,code);
        
        return ResponseEntity.ok(mapper.map(atendimento, DtoAtendimentoResponse.class));
    }
     @DeleteMapping("/delete/{code}")
     public ResponseEntity<Void> delete(@PathVariable String code){
        gestaoAtendimentoService.delete(code);
        return ResponseEntity.ok().build();
     }

}
