package br.com.ifba.prg04.vacinacao.services;


import br.com.ifba.prg04.vacinacao.repositories.LoteIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoteService implements LoteIservice{
    private final LoteIRepository loteIRepository;

}
