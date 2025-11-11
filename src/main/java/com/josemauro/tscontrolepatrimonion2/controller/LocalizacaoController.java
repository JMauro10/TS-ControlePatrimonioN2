package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.josemauro.tscontrolepatrimonion2.repository.LocalizacaoRepository;

import java.util.List;

@RestController
@RequestMapping("/localizacao")
@CrossOrigin("*")
public class LocalizacaoController {
    
    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @GetMapping
    public List<Localizacao> listarLocalizao() {
        return localizacaoRepository.findAll();
    }

    @PostMapping
    public Localizacao incluirLocalizacao(@RequestBody Localizacao localizacao){
        return localizacaoRepository.save(localizacao);
    }

    @PutMapping
    public Localizacao alterarLocalizacaoByID(@RequestBody Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    @DeleteMapping("/{id}")
    public void deletarLocalizacaoById(@PathVariable Long id){
        localizacaoRepository.deleteById(id);
    }
}
