package com.musical.musical.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="orquestra")
public class Orquestra {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="idorquestra")
    private Integer idOrquestra;

    @Column(name="Nomeorquestra", nullable = false)
    @Pattern(regexp="[A-z]{5,50}",message = "O nome orquestra deve ter no minimo 5 caracteres e no maximo 50")
    private String nomeOrquestra;

    public Integer getIdOrquestra() {
        return idOrquestra;
    }

    public void setIdOrquestra(Integer idOrquestra) {
        this.idOrquestra = idOrquestra;
    }

    public String getNomeOrquestra() {
        return nomeOrquestra;
    }

    public void setNomeOrquestra(String nomeOrquestra) {
        this.nomeOrquestra = nomeOrquestra;
    }


}
