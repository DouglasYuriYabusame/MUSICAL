package com.musical.musical.controller;

import java.util.List;

import com.musical.musical.Mensagem;
import com.musical.musical.biz.OrquestraBiz;
import com.musical.musical.entities.Orquestra;
import com.musical.musical.repository.OrquestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("orquestra")
public class OrquestraController {


    @Autowired
    private OrquestraRepository orquestraRepositorio;

    @GetMapping("listar")
    public List<Orquestra> listar(){

        List<Orquestra> lista = orquestraRepositorio.findAll();
        return lista;

    }

    @PostMapping("incluir")
    public Mensagem salvar(@RequestBody Orquestra orquestra ) {

        OrquestraBiz orquestraBiz = new OrquestraBiz();
        orquestra.setIdOrquestra(0);
        try {

            if (orquestraBiz.Validade(orquestra)) {
                this.orquestraRepositorio.save(orquestra);
                this.orquestraRepositorio.flush();
            } else {
                return orquestraBiz.msg;
            }
        } catch (Exception e) {
            orquestraBiz.msg.mensagens.add(e.getMessage());
            return orquestraBiz.msg;
        }
        orquestraBiz.msg.mensagens.add("OK");

        return orquestraBiz.msg;

    }


}