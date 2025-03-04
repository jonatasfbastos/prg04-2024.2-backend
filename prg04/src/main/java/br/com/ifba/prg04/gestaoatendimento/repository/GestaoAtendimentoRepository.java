package br.com.ifba.prg04.gestaoatendimento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ifba.prg04.gestaoatendimento.entity.GestaoAtendimento;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface GestaoAtendimentoRepository extends JpaRepository<GestaoAtendimento, Long> {
    GestaoAtendimento findGestaoAtendimentoByCodigo(String code);// buscar por codigo
    void deleteGestaoAtendimentoByCodigo(String code);// deletar por codigo e retornar
    boolean existsGestaoAtendimentoByCodigo(String code);// verificar se existe por codigo
    boolean existsGestaoAtendimentoBydataHora(LocalDateTime dataHora);// verificar se ja foi agendado no mesmo horario e data
}
