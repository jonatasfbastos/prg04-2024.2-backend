package br.com.ifba.prg04.documento.repository;

import br.com.ifba.prg04.documento.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
