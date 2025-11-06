package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Categoria;
import com.josemauro.tscontrolepatrimonion2.repository.CategoriaRepository;

@RestController
@CrossOrigin("*")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
}
