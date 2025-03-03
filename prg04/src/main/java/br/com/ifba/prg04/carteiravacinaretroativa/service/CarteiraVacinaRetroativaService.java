package br.com.ifba.prg04.carteiravacinaretroativa.service;

import br.com.ifba.prg04.infrastructure.exception.ResourceNotFoundException;
import br.com.ifba.prg04.carteiravacinaretroativa.entity.CarteiraVacinaRetroativa;
import br.com.ifba.prg04.carteiravacinaretroativa.repository.CarteiraVacinaRetroativaIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarteiraVacinaRetroativaService implements CarteiraVacinaRetroativaIService {
    private final CarteiraVacinaRetroativaIRepository vacinaRetroativaIRepository;

    @Override
    @Transactional
    public CarteiraVacinaRetroativa save(CarteiraVacinaRetroativa carteiraRetroativa) {
        if (carteiraRetroativa.getPaciente().getId() == null) {
            log.warn("O Id informado não existe");
        }
        try{
            log.info("Carteira retroativa criada com sucesso");
            return vacinaRetroativaIRepository.save(carteiraRetroativa);
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao cadastrar carteira retroativa ", e);
            throw new ResolutionException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<CarteiraVacinaRetroativa> findByIdPaciente(Pageable pageable, Long idPaciente) {
        try {
            log.info("Buscando carteira de vacinacao retroativa pelo id do paciente");
            Page<CarteiraVacinaRetroativa> carteiraVacinaRetroativas = vacinaRetroativaIRepository
                    .findByPaciente_Id(idPaciente, pageable);
            return carteiraVacinaRetroativas;
        }catch (EmptyResultDataAccessException e){
            log.error("Erro ao buscar carteira de vacinacao retroativa ", e);
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
