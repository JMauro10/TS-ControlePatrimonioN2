package com.josemauro.tscontrolepatrimonion2.entities;

import com.josemauro.tscontrolepatrimonion2.enums.MotivoBaixaEnum;
import com.josemauro.tscontrolepatrimonion2.enums.StatusInventarioEnum;

import jakarta.persistence.*;

@Entity
public class Baixa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Um Patrimonio pode ter varias Baixas (historico), 
    // mas uma Baixa pertence a um unico Patrimonio.
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "patrimonio_id")
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private String dataBaixa;

    @Enumerated(EnumType.STRING)
    @Column
    private MotivoBaixaEnum motivo;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusInventarioEnum status;

    // Uma Pessoa pode autorizar muitas Baixas.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autorizado_por_id") 
    private Pessoa autorizadoPor;

    @Column(nullable = false)
    private String nota;

    public Baixa() {
    }

    public Baixa(Long id, Patrimonio patrimonio, String dataBaixa, MotivoBaixaEnum motivo, StatusInventarioEnum status,
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

    public String getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(String dataBaixa) {
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
