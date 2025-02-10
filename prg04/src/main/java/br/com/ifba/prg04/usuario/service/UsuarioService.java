package br.com.ifba.prg04.usuario.service;

import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.usuario.entity.Usuario;
import br.com.ifba.prg04.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService implements UsuarioIService {

    private final UsuarioRepository repository;

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        log.info("Criando um novo usuário...");
        try {
            Usuario savedUsuario = repository.save(usuario);
            log.info("Usuário criado com sucesso: {}", savedUsuario.getId());
            return savedUsuario;
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao criar usuário: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        log.info("Atualizando usuário com id: {}", id);
        Usuario existingUsuario = findById(id);
        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setSenha(usuario.getSenha());
        existingUsuario.setNivelAcesso(usuario.getNivelAcesso());
        try {
            Usuario updatedUsuario = repository.save(existingUsuario);
            log.info("Usuário atualizado com sucesso: {}", updatedUsuario.getId());
            return updatedUsuario;
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao atualizar usuário: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deletando usuário com id: {}", id);
        Usuario usuario = findById(id);
        try {
            repository.delete(usuario);
            log.info("Usuário deletado com sucesso: {}", id);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao deletar usuário: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        log.info("Buscando usuário com id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado para o id: {}", id);
                    return new ResourceNotFoundException("Usuário não encontrado com o id: " + id);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        log.info("Buscando todos os usuários de forma paginada...");
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findByNome(String nome, Pageable pageable) {
        log.info("Buscando usuários pelo nome: {}", nome);
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }
}
