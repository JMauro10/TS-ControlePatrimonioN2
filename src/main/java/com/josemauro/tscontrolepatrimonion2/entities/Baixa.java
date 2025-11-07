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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Baixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Um Patrimonio pode ter varias Baixas (historico), 
    // mas uma Baixa pertence a um unico Patrimonio.
    @ManyToOne 
    @JoinColumn(name = "patrimonio_id")
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private Date dataBaixa;

    @Enumerated(EnumType.STRING)
    @Column
    private MotivoBaixaEnum motivo;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusInventarioEnum status;

    // Uma Pessoa pode autorizar muitas Baixas.
    @ManyToOne
    @JoinColumn(name = "autorizado_por_id") 
    private Pessoa autorizadoPor;

    @Column(nullable = false)
    private String nota;

    public Baixa() {
    }

    public Baixa(Long id, Patrimonio patrimonio, Date dataBaixa, MotivoBaixaEnum motivo, StatusInventarioEnum status,
            Pessoa autorizadoPor, String nota) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.dataBaixa = dataBaixa;
        this.motivo = motivo;
        this.status = status;
        this.autorizadoPor = autorizadoPor;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public MotivoBaixaEnum getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoBaixaEnum motivo) {
        this.motivo = motivo;
    }

    public StatusInventarioEnum getStatus() {
        return status;
    }

    public void setStatus(StatusInventarioEnum status) {
        this.status = status;
    }

    public Pessoa getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(Pessoa autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
}
