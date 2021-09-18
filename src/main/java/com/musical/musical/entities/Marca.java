package com.musical.musical.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="marca")
public class Marca {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="idmarca")
    private Integer idMarca;

    @Column(name="Nomemarca", nullable = false)
    @Pattern(regexp="[A-z]{5,50}",message = "O nome marca deve ter no minimo 5 caracteres e no maximo 50")
    private String nomeMarca;


    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public void setNomeMarca(String nomeMarca) {
        this.nomeMarca = nomeMarca;
    }
}
