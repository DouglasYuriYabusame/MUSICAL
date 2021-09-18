package com.musical.musical.controller;

import com.musical.musical.Mensagem;
import com.musical.musical.biz.MarcaBiz;
import com.musical.musical.entities.Marca;
import com.musical.musical.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRepository;


    @GetMapping("listar")
    public List<Marca> listar(){

        List<Marca> lista = marcaRepository.findAll();
        return lista;

    }

    @PostMapping("incluir")
    public Mensagem salvar(@RequestBody Marca marca ) {

        MarcaBiz marcaBiz = new MarcaBiz();
        marca.setIdMarca(0);
        try {

            if (marcaBiz.Validade(marca)) {
                this.marcaRepository.save(marca);
                this.marcaRepository.flush();
            } else {
                return marcaBiz.msg;
            }
        } catch (Exception e) {
            marcaBiz.msg.mensagens.add(e.getMessage());
            return marcaBiz.msg;
        }
        marcaBiz.msg.mensagens.add("OK");

        return marcaBiz.msg;

    }
}
