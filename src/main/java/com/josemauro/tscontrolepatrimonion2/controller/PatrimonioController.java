package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Patrimonio;
import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;

@RestController
@CrossOrigin("*")
public class PatrimonioController {
    
    @Autowired
    private PatrimonioRepository patrimonioRepository;
}
