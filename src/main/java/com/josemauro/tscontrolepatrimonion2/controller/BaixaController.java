package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Baixa;
import com.josemauro.tscontrolepatrimonion2.repository.BaixaRepository;

@RestController
@CrossOrigin("*")
public class BaixaController {

    @Autowired
    private BaixaRepository baixaRepository;
    
}
