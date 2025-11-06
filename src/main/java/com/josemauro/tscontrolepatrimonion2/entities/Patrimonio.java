package com.josemauro.tscontrolepatrimonion2.entities;

import java.sql.Date;
import java.time.LocalDate;

import com.josemauro.tscontrolepatrimonion2.enums.StatusPatrimonioEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Patrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String qrcode;

    @Column
    private String tag;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusPatrimonioEnum status = StatusPatrimonioEnum.ATIVO;

    @Column
    private Date dataaquisicao;

    @Column
    private double custo;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @ManyToOne
    @JoinColumn(name = "pessoaResponsavel_id")
    private Pessoa pessoaResponsavel;

    public Patrimonio() {
    }

    public Patrimonio(Long id, String qrcode, String tag, String descricao, Categoria categoria,
            StatusPatrimonioEnum status, Date dataaquisicao, double custo, Localizacao localizacao,
            Pessoa pessoaResponsavel) {
        this.id = id;
        this.qrcode = qrcode;
        this.tag = tag;
        this.descricao = descricao;
        this.categoria = categoria;
        this.status = status;
        this.dataaquisicao = dataaquisicao;
        this.custo = custo;
        this.localizacao = localizacao;
        this.pessoaResponsavel = pessoaResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public StatusPatrimonioEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPatrimonioEnum status) {
        this.status = status;
    }

    public Date getDataaquisicao() {
        return dataaquisicao;
    }

    public void setDataaquisicao(Date dataaquisicao) {
        this.dataaquisicao = dataaquisicao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Pessoa getPessoaResponsavel() {
        return pessoaResponsavel;
    }

    public void setPessoaResponsavel(Pessoa pessoaResponsavel) {
        this.pessoaResponsavel = pessoaResponsavel;
    }

    

    
    
}
