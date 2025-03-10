package br.com.ifba.prg04.familiaTest;

import br.com.ifba.prg04.familia.controller.FamiliaController;
import br.com.ifba.prg04.familia.dto.FamiliaPostRequestDTO;
import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.service.FamiliaService;
import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.paciente.entity.Paciente;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class FamiliaControllerTest {

    private MockMvc mockMvc; //simula requisicoes HTTP

    @Mock
    private FamiliaService familiaService; //simula o Service

    @InjectMocks
    private FamiliaController familiaController; //injeta o Controller

    private ObjectMapper objectMapper = new ObjectMapper(); //converte objetos para JSON

    @BeforeEach
    void setUp() {
        //inicializa o MockMvc com o controller real (sem precisar de @WebMvcTest)
        mockMvc = MockMvcBuilders.standaloneSetup(familiaController).build();
    }

    @Test
    public void testCriarFamilia() throws Exception {
        //cria um responsável
        Funcionario responsavel = new Funcionario();
        responsavel.setId(1L);
        responsavel.setNome("Dr. João Silva");
        
        Paciente paciente1 = new Paciente();
        paciente1.setNome("Lucas");
        paciente1.setCpf("123.123.123-12");
        
        Paciente paciente2 = new Paciente();
        paciente2.setNome("Pedro");
        paciente2.setCpf("122.123.123-12");
        
        List<Paciente> pacientes = new ArrayList<>();

        pacientes.add(paciente1);
        pacientes.add(paciente2);
        

        //cria DTO com membros preenchidos
        FamiliaPostRequestDTO familiaDTO = new FamiliaPostRequestDTO();
        familiaDTO.setNome("Família Souza");
        familiaDTO.setEndereco("Rua X, 123");
        familiaDTO.setResponsavel_id(responsavel.getId());
        familiaDTO.setMembros(pacientes);

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
                .andExpect(jsonPath("$.membros[0]").value("Lucas"))
                .andExpect(jsonPath("$.membros[1]").value("Pedro"));
    }

    @Test
    void testBuscarFamiliasPorNome() throws Exception {
        // Crie a família simulada
        Familia familia = new Familia();
        familia.setNome("Familia Souza");
        familia.setEndereco("Rua X, 123");
        Funcionario responsavel = new Funcionario();
        responsavel.setNome("Dr. João Silva");

        Paciente paciente1 = new Paciente();
        paciente1.setNome("Lucas");
        paciente1.setCpf("123.123.123-12");
        
        Paciente paciente2 = new Paciente();
        paciente2.setNome("Pedro");
        paciente2.setCpf("122.123.123-12");
        
        List<Paciente> pacientes = new ArrayList<>();

        pacientes.add(paciente1);
        pacientes.add(paciente2);
        familia.setResponsavel(responsavel);
        familia.setMembros(pacientes);

        // Simula a resposta do serviço
        Mockito.when(familiaService.findByName("Familia Souza")).thenReturn(List.of(familia));

        // Chama o mockMvc para verificar a resposta
        mockMvc.perform(get("/familias/findByName/{nome}", "Familia Souza"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Familia Souza"))
                .andExpect(jsonPath("$[0].membros[0]").value("Lucas"))
                .andExpect(jsonPath("$[0].membros[1]").value("Pedro"));
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

        // Criar o Pageable para simular a página 0 com tamanho 10
        Pageable pageable = PageRequest.of(0, 10);

        // Simula o comportamento do service
        Mockito.when(familiaService.findAll(pageable)).thenReturn(pageFamilias);

        // Simula a requisição com os parâmetros de paginação
        mockMvc.perform(get("/familias/findAll")
                        .param("page", "0")  // Página 0
                        .param("size", "10")) // Tamanho da página 10
                .andExpect(status().isOk())  // Esperando status 200
                .andExpect(jsonPath("$").isArray()) // Verificando se o retorno é um array
                .andExpect(jsonPath("$.length()").value(2)); // Verificando que a resposta tem 2 elementos
    }
}

