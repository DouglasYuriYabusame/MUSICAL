package com.musical.musical.controller;

import java.util.List;

import com.musical.musical.Mensagem;
import com.musical.musical.biz.InstrumentoBiz;
import com.musical.musical.entities.Instrumento;
import com.musical.musical.repository.InstrumentoRepository;
import com.musical.musical.repository.MarcaRepository;
import com.musical.musical.repository.OrquestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("instrumento")
public class InstrumentoController {


    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private OrquestraRepository orquestraRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("listar")
    public List<Instrumento> listar(){

        List<Instrumento> lista = instrumentoRepository.findAll();
        return lista;

    }

    @PostMapping("incluir")
    public Mensagem salvar(@RequestBody Instrumento instrumento ) {

        instrumento.setIdInstrumento(0);
        InstrumentoBiz instrumentoBiz = new InstrumentoBiz(orquestraRepository,marcaRepository);

        try {

            if (instrumentoBiz.Validade(instrumento)) {
                this.instrumentoRepository.save(instrumento);
                this.instrumentoRepository.flush();
            } else {
                return instrumentoBiz.msg;
            }
        } catch (Exception e) {
            instrumentoBiz.msg.mensagens.add(e.getMessage());
            return instrumentoBiz.msg;
        }
        instrumentoBiz.msg.mensagens.add("OK");

        return instrumentoBiz.msg;

    }

}


