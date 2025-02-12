package br.com.ifba.prg04.usuario.service;

import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.usuario.entity.Usuario;
import br.com.ifba.prg04.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Vittorio Mina");
        usuario.setEmail("vittoriomina@email.com");
        usuario.setSenha("senha123");
        usuario.setNivelAcesso("ADMIN");
    }

    @Test
    void shouldCreateUserSuccessfully() {
        when(repository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario savedUser = service.create(usuario);

        assertNotNull(savedUser);
        assertEquals("Vittorio Mina", savedUser.getNome());
        assertEquals("vittoriomina@email.com", savedUser.getEmail());
        verify(repository, times(1)).save(usuario);
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        when(repository.save(any(Usuario.class))).thenThrow(new DataIntegrityViolationException("Usuário já existe"));

        assertThrows(DataIntegrityViolationException.class, () -> service.create(usuario));

        verify(repository, times(1)).save(usuario);
    }

    @Test
    void shouldFindUserById() {
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario foundUser = service.findById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));

        verify(repository, times(1)).findById(1L);
    }
}
