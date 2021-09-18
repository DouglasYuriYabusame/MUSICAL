package com.musical.musical.biz;

import com.musical.musical.Mensagem;
import com.musical.musical.entities.Instrumento;
import com.musical.musical.entities.Marca;

import javax.validation.*;
import java.util.Set;

public class MarcaBiz {

    public Mensagem msg;

    public MarcaBiz() {
        msg = new Mensagem();
    }

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public Boolean Validade(Marca marca) {

        Set<ConstraintViolation<Marca>> constraintViolationSet = validator.validate(marca);

        Boolean result = true;
        if (marca.getNomeMarca().isEmpty()) {
            msg.mensagens.add("O nome da marca não pode ser vazio");
            result = false;
        }
        if (marca.getNomeMarca().startsWith("PP")) {
            msg.mensagens.add("O nome da marca é inválido");
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
