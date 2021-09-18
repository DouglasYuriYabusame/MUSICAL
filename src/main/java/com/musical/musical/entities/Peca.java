package com.musical.musical.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="peca")
public class Peca {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="idpeca")
    private Integer idPeca;

    @Column(name="Nomepeca", nullable = false)
    @Pattern(regexp="[A-z]{5,50}", message = "O nome peca deve ter no minimo 5 caracteres e no maximo 50")
    private String nomePeca;

    @Column(name="idinstrumento")
    @NotNull(message = "O id do Instrumento nao pode ser nulo")
    private Integer idInstrumento;


    public Integer getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
    }
}