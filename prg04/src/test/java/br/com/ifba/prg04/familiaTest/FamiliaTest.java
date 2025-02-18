package br.com.ifba.prg04.familiaTest;

import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.repository.FamiliaRepository;
import br.com.ifba.prg04.funcionario.entities.Funcionario;
import br.com.ifba.prg04.funcionario.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//classe de teste de familia
@SpringBootTest
@Transactional
public class FamiliaTest {

    @Autowired
    private FamiliaRepository familiaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private Funcionario responsavel;
    private Familia familia;

    @BeforeEach
    public void setUp() {
        // Limpa os dados antes de cada teste
        familiaRepository.deleteAll();
        funcionarioRepository.deleteAll();

        //criar um funcionário responsavel para os testes
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigo("12348955");
        funcionario.setLogin("joao12");
        funcionario.setSenha("senha12");
        funcionario.setCategoria("Admin");
        funcionario.setNome("Joãoo");
        funcionario.setCpf("123456788");
        funcionario.setEndereco("Rua X, 122");
        funcionario.setTelefone("9999999998");

        funcionarioRepository.save(funcionario);

        //atribuindo ao campo responsavel
        this.responsavel = funcionario;

        Familia familia = new Familia();
        familia.setNome("Família Silvaaa");
        familia.setEndereco("Rua Y, 456");
        familia.setResponsavel(funcionario); //atribui o funcionario como responsavel

        this.familia = familiaRepository.save(familia);
    }

    @AfterEach
    public void tearDown() {
        //limpa os dados apos cada teste
        familiaRepository.deleteAll();
        funcionarioRepository.deleteAll();
    }

    @Test
    public void testCriarFamilia() {
        Familia familia = new Familia();
        familia.setNome("Silvaaa");
        familia.setEndereco("Rua A, 123");
        familia.setResponsavel(responsavel);  //associa o responsavel

        Familia familiaSalva = familiaRepository.save(familia);

        assertNotNull(familiaSalva.getId(), "O ID da família não deve ser nulo após a persistência");
        assertEquals("Silvaaa", familiaSalva.getNome(), "O nome da família deve ser o mesmo");
        assertEquals(responsavel, familiaSalva.getResponsavel(), "O responsável da família deve ser o mesmo");
    }

    @Test
    public void testBuscarFamiliaPorId() {
        //busca a familia criada no setUp
        Familia familiaEncontrada = familiaRepository.findById(familia.getId()).orElse(null);

        //verifica se a família foi encontrada
        assertNotNull(familiaEncontrada, "A família deve ser encontrada no banco de dados");
        assertEquals("Família Silvaaa", familiaEncontrada.getNome(), "O nome da família deve ser o mesmo");
        assertEquals(responsavel, familiaEncontrada.getResponsavel(), "O responsável da família deve ser o mesmo");
    }

    @Test
    public void testAtualizarFamilia() {
        // Busca a família criada no setUp
        Familia familiaExistente = familiaRepository.findById(familia.getId())
                .orElseThrow(() -> new RuntimeException("Família não encontrada."));

        // Dados atualizados
        String novoNome = "Família Oliveira";
        String novoEndereco = "Rua Z, 789";
        Funcionario novoResponsavel = new Funcionario();
        novoResponsavel.setCodigo("987654321");
        novoResponsavel.setLogin("maria123");
        novoResponsavel.setSenha("senha123");
        novoResponsavel.setCategoria("User");
        novoResponsavel.setNome("Maria Oliveira");
        novoResponsavel.setCpf("987654321");
        novoResponsavel.setEndereco("Rua W, 456");
        novoResponsavel.setTelefone("888888888");

        // Salva o novo responsável no banco de dados
        novoResponsavel = funcionarioRepository.save(novoResponsavel);

        // Atualiza os dados da família
        familiaExistente.setNome(novoNome);
        familiaExistente.setEndereco(novoEndereco);
        familiaExistente.setResponsavel(novoResponsavel);

        // Salva a família atualizada
        Familia familiaAtualizada = familiaRepository.save(familiaExistente);

        // Busca a família atualizada no banco de dados
        Familia familiaRecuperada = familiaRepository.findById(familiaAtualizada.getId())
                .orElseThrow(() -> new RuntimeException("Família não encontrada após a atualização."));

        // Verifica se os dados foram atualizados corretamente
        assertNotNull(familiaRecuperada, "A família atualizada não deve ser nula.");
        assertEquals(novoNome, familiaRecuperada.getNome(), "O nome da família deve ser atualizado.");
        assertEquals(novoEndereco, familiaRecuperada.getEndereco(), "O endereço da família deve ser atualizado.");
        assertEquals(novoResponsavel, familiaRecuperada.getResponsavel(), "O responsável da família deve ser atualizado.");
    }
}
