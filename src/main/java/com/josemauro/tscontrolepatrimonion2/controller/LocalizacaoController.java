package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import com.josemauro.tscontrolepatrimonion2.repository.LocalizaoRepository;

@RestController
@CrossOrigin("*")
public class LocalizacaoController {
    
    @Autowired
    private LocalizaoRepository localizaoRepository;
}
