package br.com.ifba.prg04.familiaTest;

import br.com.ifba.prg04.GestaoFuncionario.entities.Funcionario;
import br.com.ifba.prg04.familia.controller.FamiliaController;
import br.com.ifba.prg04.familia.dto.FamiliaPostRequestDTO;
import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.service.FamiliaService;
import br.com.ifba.prg04.infrastructure.mapper.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FamiliaController.class)
//@Import(ObjectMapperUtil.class)
//@ExtendWith(MockitoExtension.class)
public class FamiliaControllerTest {

    @Autowired
    private MockMvc mockMvc; //simula requisicoes HTTP

    @MockitoBean
    private FamiliaService familiaService; //simula o Service

   /* @InjectMocks*/
    private FamiliaController familiaController; //injeta o Controller

    @MockitoBean
    private ObjectMapperUtil objectMapperUtil;
    @Autowired
    private ObjectMapper objectMapper; //converte objetos para JSON

   /* @BeforeEach
    void setUp() {
        //inicializa o MockMvc com o controller real (sem precisar de @WebMvcTest)
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(familiaController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }*/

    @Test
    public void testCriarFamilia() throws Exception {
        //cria um responsável
        Funcionario responsavel = new Funcionario();
        responsavel.setId(1L);
        responsavel.setNome("Dr. João Silva");

        //cria DTO com membros preenchidos
        FamiliaPostRequestDTO familiaDTO = new FamiliaPostRequestDTO();
        familiaDTO.setNome("Familia Souza");
        familiaDTO.setEndereco("Rua X, 123");
        familiaDTO.setResponsavel(responsavel.getNome());
        familiaDTO.setMembros(List.of("Maria Souza", "Carlos Oliveira"));

        Familia familia = new Familia();
        familia.setId(1L);
        familia.setNome(familiaDTO.getNome());
        familia.setEndereco(familiaDTO.getEndereco());
        familia.setResponsavel(responsavel);
        familia.setMembros(familiaDTO.getMembros());

        //simula o comportamento do service
        Mockito.when(familiaService.save(Mockito.any(Familia.class))).thenReturn(familia);

        mockMvc.perform(post("/familias/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(familiaDTO)))
                .andExpect(status().isCreated()) //retornar 201 se der tudo certo
                .andExpect(jsonPath("$.nome").value("Familia Souza"))
                .andExpect(jsonPath("$.membros[0]").value("Maria Souza"))
                .andExpect(jsonPath("$.membros[1]").value("Carlos Oliveira"));
    }

    @Test
    void testBuscarFamiliasPorNome() throws Exception {
        // Crie a família simulada
        Familia familia = new Familia();
        familia.setNome("Familia Souza");
        familia.setEndereco("Rua X, 123");
        Funcionario responsavel = new Funcionario();
        responsavel.setNome("Dr. João Silva");
        familia.setResponsavel(responsavel);
        familia.setMembros(List.of("Maria Souza", "Carlos Oliveira"));

        // Simula a resposta do serviço
        Mockito.when(familiaService.findByName("Familia Souza")).thenReturn(List.of(familia));

        // Chama o mockMvc para verificar a resposta
        mockMvc.perform(get("/familias/findByName/{nome}", "Familia Souza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Familia Souza"))
                .andExpect(jsonPath("$[0].membros[0]").value("Maria Souza"))
                .andExpect(jsonPath("$[0].membros[1]").value("Carlos Oliveira"));
    }

    @Test
    void testListarFamilias() throws Exception {
        // Simular algumas famílias
        Familia familia1 = new Familia();
        familia1.setNome("Familia Souza");

        Familia familia2 = new Familia();
        familia2.setNome("Familia Silva");

        // Criar uma lista de famílias paginada
        List<Familia> familias = List.of(familia1, familia2);
        Page<Familia> pageFamilias = new PageImpl<>(familias);

        Pageable pageable = PageRequest.of(0, 10);

        // Simula o comportamento do service
        Mockito.when(familiaService.findAll(pageable)).thenReturn(pageFamilias);

        // Simula a requisição com os parâmetros de paginação
        mockMvc.perform(get("/familias/findAll")
                        .param("page", "0")  // Página 0
                        .param("size", "10") // Tamanho da página 10
                        .param("sort", "nome,asc"))
                .andExpect(status().isOk())  // Esperando status 200
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(2));
    }
}

