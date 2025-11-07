package com.josemauro.tscontrolepatrimonion2.entities;

import java.sql.Date;

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
public class Inventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Um Patrimonio pode estar em muitos Inventarios (ex: um por ano).
    @ManyToOne
    @JoinColumn(name = "patrimonio_id")
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private Date dataInventario;
    
    @Enumerated(EnumType.STRING)
    @Column
    private StatusInventarioEnum status;

    // Uma Localizacao pode aparecer em muitos registros de Inventario.
    @ManyToOne
    @JoinColumn(name = "localizacao_fisica_id")
    private Localizacao localizacaoFisica; // localizacao onde foi achado

    // Uma Pessoa (auditor) pode realizar muitos Inventarios.
    @ManyToOne
    @JoinColumn(name = "auditor_id")
    private Pessoa auditor;

    @Column(nullable = false)
    private String nota;

    public Inventario() {
    }

    public Inventario(Long id, Patrimonio patrimonio, Date dataInventario, StatusInventarioEnum status,
            Localizacao localizacaoFisica, Pessoa auditor, String nota) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.dataInventario = dataInventario;
        this.status = status;
        this.localizacaoFisica = localizacaoFisica;
        this.auditor = auditor;
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

    public Date getDataInventario() {
        return dataInventario;
    }

    public void setDataInventario(Date dataInventario) {
        this.dataInventario = dataInventario;
    }

    public StatusInventarioEnum getStatus() {
        return status;
    }

    public void setStatus(StatusInventarioEnum status) {
        this.status = status;
    }

    public Localizacao getLocalizacaoFisica() {
        return localizacaoFisica;
    }

    public void setLocalizacaoFisica(Localizacao localizacaoFisica) {
        this.localizacaoFisica = localizacaoFisica;
    }

    public Pessoa getAuditor() {
        return auditor;
    }

    public void setAuditor(Pessoa auditor) {
        this.auditor = auditor;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
    
}
