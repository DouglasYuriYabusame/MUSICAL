package com.musical.musical.controller;

import com.musical.musical.Mensagem;
import com.musical.musical.biz.InstrumentoBiz;
import com.musical.musical.biz.PecaBiz;
import com.musical.musical.entities.Peca;
import com.musical.musical.entities.Marca;
import com.musical.musical.repository.InstrumentoRepository;
import com.musical.musical.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peca")
public class PecaController {
    @Autowired
    private PecaRepository pecaRepository;

    @Autowired
    private InstrumentoRepository instrumentoRepository;


    @GetMapping("listar")
    public List<Peca> listar(){

        List<Peca> lista = pecaRepository.findAll();
        return lista;

    }

    @PostMapping("incluir")
    public Mensagem salvar(@RequestBody Peca peca ) {

        PecaBiz pecaBiz = new PecaBiz(instrumentoRepository);
        peca.setIdPeca(0);
        try {

            if (pecaBiz.Validade(peca)) {
                this.pecaRepository.save(peca);
                this.pecaRepository.flush();
            } else {
                return pecaBiz.msg;
            }
        } catch (Exception e) {
            pecaBiz.msg.mensagens.add(e.getMessage());
            return pecaBiz.msg;
        }
        pecaBiz.msg.mensagens.add("OK");

        return pecaBiz.msg;

    }
}
