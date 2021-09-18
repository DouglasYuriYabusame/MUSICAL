package com.musical.musical.biz;


import com.musical.musical.Mensagem;
import com.musical.musical.entities.Instrumento;
import com.musical.musical.entities.Orquestra;
import com.musical.musical.repository.MarcaRepository;
import com.musical.musical.repository.OrquestraRepository;

import javax.validation.*;
import java.util.Set;

public class InstrumentoBiz {
    public Mensagem msg;

    private OrquestraRepository orquestraRepository;
    private MarcaRepository marcaRepository;

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public InstrumentoBiz(OrquestraRepository orquestraRepository, MarcaRepository marcaRepository) {
        msg = new Mensagem();
        this.orquestraRepository = orquestraRepository;
        this.marcaRepository = marcaRepository;
    }

    public Boolean Validade(Instrumento instrumento) {

        Set<ConstraintViolation<Instrumento>> constraintViolationSet = validator.validate(instrumento);

        Boolean result = true;
        if (instrumento.getNomeInstrumento().isEmpty()) {
            msg.mensagens.add("O nome do instrumento não pode ser vazio");
            result = false;
        }
        if (instrumento.getNomeInstrumento().startsWith("PP")) {
            msg.mensagens.add("O nome do instrumento é inválido");
            result = false;
        }
        if (orquestraRepository.findById(instrumento.getIdOrquestra()).isEmpty()) {
            msg.mensagens.add("Id inexistente");
            result = false;
        }
        if (marcaRepository.findById(instrumento.getIdMarca()).isEmpty()) {
            msg.mensagens.add("Id inexistente");
            result = false;
        }
        if(!constraintViolationSet.isEmpty()){
            ConstraintViolationException exceptionConstraint = new ConstraintViolationException(constraintViolationSet);
            msg.mensagens.add(exceptionConstraint.getMessage());
            result = false;
        }
        if(result == true){
            msg.mensagens.add("OK");
        }


        return result;

    }

}
