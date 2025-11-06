package com.josemauro.tscontrolepatrimonion2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Baixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Baixa() {
    }

    
}
