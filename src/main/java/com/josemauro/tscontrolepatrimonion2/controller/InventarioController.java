package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.josemauro.tscontrolepatrimonion2.entities.Inventario;
import com.josemauro.tscontrolepatrimonion2.repository.InventarioRepository;

@RestController
@CrossOrigin("*")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;
}
