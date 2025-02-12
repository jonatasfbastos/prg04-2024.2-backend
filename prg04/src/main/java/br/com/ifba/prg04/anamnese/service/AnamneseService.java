package br.com.ifba.prg04.anamnese.service;

import br.com.ifba.prg04.anamnese.entity.Anamnese;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnamneseService implements AnamneseIService{

    @Override
    public void save(Anamnese anamnese) {
        
    }
}
