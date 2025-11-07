package com.josemauro.tscontrolepatrimonion2.entities;

import java.sql.Date;

import com.josemauro.tscontrolepatrimonion2.enums.StatusInventarioEnum;
import com.josemauro.tscontrolepatrimonion2.enums.StatusPatrimonioEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // informar o relacionamento @
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private Date dataInventario;
    
    @Enumerated(EnumType.STRING)
    @Column
    private StatusInventarioEnum status;

    // informar o relacionamento @
    private Localizacao localizacaoFisica; // localizacao onde foi achado

    // informar o relacionamento @
    private Pessoa auditor;

    @Column(nullable = false)
    private String nota;

    public Inventario() {
    }
    
}
