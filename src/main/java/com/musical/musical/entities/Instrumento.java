package com.musical.musical.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="instrumento")
public class Instrumento {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="idinstrumento")
    private Integer idInstrumento;

    @Column(name="Nomeinstrumento", nullable = false)
    @Pattern(regexp="[A-z]{5,50}", message = "O nome instrumento deve ter no minimo 5 caracteres e no maximo 50")
    private String nomeInstrumento;

    @Column (name="idorquestra", nullable = false)
    private Integer idOrquestra;

    @Column (name="idmarca", nullable= false)
    private Integer idMarca;


    public Integer getIdInstrumento() {
        return idInstrumento;
    }

    public void setIdInstrumento(Integer idInstrumento) {
        this.idInstrumento = idInstrumento;
    }

    public String getNomeInstrumento() {
        return nomeInstrumento;
    }

    public void setNomeInstrumento(String nomeInstrumento) {
        this.nomeInstrumento = nomeInstrumento;
    }

    public Integer getIdOrquestra() {
        return idOrquestra;
    }

    public void setIdOrquestra(Integer idOrquestra) {
        this.idOrquestra = idOrquestra;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }
}
