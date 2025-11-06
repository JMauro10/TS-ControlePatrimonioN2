package com.josemauro.tscontrolepatrimonion2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Movimentacao() {
    }

    
}
