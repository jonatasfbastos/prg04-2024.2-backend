package br.com.ifba.prg04.usuario.service;

import br.com.ifba.prg04.usuario.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioIService {

    Usuario create(Usuario usuario);

    Usuario update(Long id, Usuario usuario);

    void delete(Long id);

    Usuario findById(Long id);

    Page<Usuario> findAll(Pageable pageable);

    Page<Usuario> findByNome(String nome, Pageable pageable);
}
