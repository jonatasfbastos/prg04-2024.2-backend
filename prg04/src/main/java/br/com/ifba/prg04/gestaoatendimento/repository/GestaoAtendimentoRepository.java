package br.com.ifba.prg04.gestaoatendimento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface GestaoAtendimentoRepository extends JpaRepository<GestaoAtendimento, Long> {
    GestaoAtendimento findAtendimentoByCode(String code);
    void deleteAtendimentoByCode(String code);
    boolean existsAtendimentoByCode(String code);
    boolean existsAtendimentoBydataHora(LocalDateTime dataHora);
     List<GestaoAtendimento> findByUsuarioNome(String usuarioNome);
}
