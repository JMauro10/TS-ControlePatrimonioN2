package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;

@RestController
@CrossOrigin("*")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    
}
