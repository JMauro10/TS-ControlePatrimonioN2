package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Movimentacao;
import com.josemauro.tscontrolepatrimonion2.repository.MovimentacaoRepository;

@RestController
@CrossOrigin("*")
public class MovimentacaoController {
    
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
}
