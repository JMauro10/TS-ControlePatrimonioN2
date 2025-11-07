package com.josemauro.tscontrolepatrimonion2.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // informar o relacionamento @
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private Date dataMovimentacao;

    // informar o relacionamento @
    private Localizacao deLocalizacao;

    // informar o relacionamento @
    private Localizacao paraLocalizacao;

    // informar o relacionamento @
    private Pessoa deResponsavel;

    // informar o relacionamento @
    private Pessoa paraResponsavel;

    @Column(nullable = false)
    private String nota;

    // informar o relacionamento @
    // é o mesmo pessoa responsavel?
    private Pessoa criadorMovimento;

    public Movimentacao() {
    }
    
}
