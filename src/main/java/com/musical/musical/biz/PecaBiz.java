package com.musical.musical.biz;

import com.musical.musical.Mensagem;
import com.musical.musical.entities.Instrumento;
import com.musical.musical.entities.Marca;
import com.musical.musical.entities.Peca;
import com.musical.musical.repository.InstrumentoRepository;
import com.musical.musical.repository.PecaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.*;
import java.util.Set;

public class PecaBiz {

    public Mensagem msg;

    private InstrumentoRepository instrumentoRepository;

    public PecaBiz(InstrumentoRepository instrumentoRepository) {
        msg = new Mensagem();
        this.instrumentoRepository = instrumentoRepository;
    }

    public Boolean Validade(Peca peca) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Peca>> constraintViolationSet = validator.validate(peca);

        Boolean result = true;
        if (peca.getNomePeca().isEmpty()) {
            msg.mensagens.add("O nome da peca não pode ser vazio");
            result = false;
        }
        if (peca.getNomePeca().startsWith("PP")) {
            msg.mensagens.add("O nome da peca é inválido");
            result = false;
        }
        if (instrumentoRepository.findById(peca.getIdInstrumento()).isEmpty()){
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