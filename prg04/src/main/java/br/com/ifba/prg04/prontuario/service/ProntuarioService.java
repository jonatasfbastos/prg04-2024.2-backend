//package br.com.ifba.prg04.prontuario.service;
//
//import br.com.ifba.prg04.prontuario.repository.ProntuarioRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class ProntuarioService implements ProntuarioIService{
//
//    private final ProntuarioRepository prontuarioRepository;
//
////    @Transactional
////    @Override
////    public void save(Prontuario prontuario) {
////
////        try {
////            prontuarioRepository.save(prontuario);
////        }catch (Exception e) {
////            throw new BusinessException("Erro ao tentar salvar os dados", e);
////        }
////    }
////
////    @Transactional
////    @Override
////    public void savePaciente(Paciente paciente) {
////
////        Prontuario prontuario = new Prontuario();
////
////        try {
////
////            prontuario.setPaciente(paciente);
////
////            prontuarioRepository.save(prontuario);
////        }catch (Exception e) {
////            throw new BusinessException("Erro ao tentar salvar os dados do paciente", e);
////        }
////
////    }
////
////    @Transactional
////    @Override
////    public void saveAnamnese(Paciente paciente , Anamnese anamnese) {
////
////        Prontuario prontuario = new Prontuario();
////
////        try {
////            if(paciente.getNome().isEmpty() || paciente.getCpf().isEmpty()){
////                log.info("Os dados de nome ou cpf estão vazios !!");
////            }
////            else{
////                prontuario = prontuarioRepository.findByNomeOrCpf(paciente.getNome(), paciente.getCpf());
////
////                if (prontuarioRepository.findaById(prontuario.getId())){
////                    if (anamnese != null){
////                        prontuario.getAnamneses().add(anamnese);
////                        prontuarioRepository.save(prontuario);
////                    }
////                    else
////                        log.error("Dados de anamneses incompletos");
////                }else
////                    log.error("Dados do paciente não encontrado");
////            }
////        } catch (Exception e) {
////            throw new BusinessException("Erro ao tentar salvar os dados de Anamnese", e);
////        }
////    }
////
////    @Transactional
////    @Override
////    public void update(Paciente paciente) {
////
////        Prontuario prontuario = new Prontuario();
////
////        try {
////            prontuario = prontuarioRepository.findByNomeOrCpf(paciente.getNome(), paciente.getCpf());
////
////            if(prontuarioRepository.findaById(prontuario.getId())) {
////                prontuario.setPaciente(paciente);
////
////                prontuarioRepository.save(prontuario);
////            }
////            else
////                log.error("Dados do paciente não encontrado");
////        }catch (Exception e) {
////            throw new BusinessException("Erro ao tentar atualizar os dados do paciente", e);
////        }
////    }
////
////    @Transactional
////    @Override
////    public Prontuario findByPaciente(Paciente paciente) {
////        try {
////            return prontuarioRepository.findByNomeOrCpf(paciente.getNome(), paciente.getCpf());
////        } catch (Exception e) {
////            throw new BusinessException("Erro ao tentar buscar os dados",e);
////        }
////    }
//
//}
