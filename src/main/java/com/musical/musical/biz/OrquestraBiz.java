package com.musical.musical.biz;

import com.musical.musical.Mensagem;
import com.musical.musical.entities.Marca;
import com.musical.musical.entities.Orquestra;

import javax.validation.*;
import java.util.Set;

public class OrquestraBiz {

    public Mensagem msg;

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public OrquestraBiz() {
        msg = new Mensagem();
    }

    public Boolean Validade(Orquestra orquestra) {
        Set<ConstraintViolation<Orquestra>> constraintViolationSet = validator.validate(orquestra);

        Boolean result = true;
        if (orquestra.getNomeOrquestra().isEmpty()) {
            msg.mensagens.add("O nome da orquestra não pode ser vazio");
            result = false;
        }
        if (orquestra.getNomeOrquestra().startsWith("PP")) {
            msg.mensagens.add("O nome da orquestra é inválido");
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
