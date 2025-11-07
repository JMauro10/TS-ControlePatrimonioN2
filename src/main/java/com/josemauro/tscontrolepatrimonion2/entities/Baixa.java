package com.josemauro.tscontrolepatrimonion2.entities;

import java.sql.Date;

import com.josemauro.tscontrolepatrimonion2.enums.MotivoBaixaEnum;
import com.josemauro.tscontrolepatrimonion2.enums.StatusInventarioEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Baixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // informar o relacionamento @
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private Date dataBaixa;

    @Enumerated(EnumType.STRING)
    @Column
    private MotivoBaixaEnum motivo;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusInventarioEnum status;

    // informar o relacionamento @
    private Pessoa autorizadoPor;

    @Column(nullable = false)
    private String nota;

    public Baixa() {
    }

    
}
