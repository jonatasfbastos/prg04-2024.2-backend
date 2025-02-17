package br.com.ifba.prg04.usuario.repository;

import br.com.ifba.prg04.usuario.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Usuario findByNome(String nome);
}
