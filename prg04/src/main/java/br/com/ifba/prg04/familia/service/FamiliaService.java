package br.com.ifba.prg04.familia.service;

import br.com.ifba.prg04.familia.entity.Familia;
import br.com.ifba.prg04.familia.repository.FamiliaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j //logger
public class FamiliaService implements FamiliaIService {

    private final FamiliaRepository familiaRepository;

    @Override
    public Familia salvar(Familia familia) {
        return null;
    }

    @Override
    public Familia update(int id, Familia familia) {
        return null;
    }

    @Override
    public Familia findById(int id) {
        return null;
    }

    @Override
    public List<Familia> findAll() {
        return List.of();
    }
}
