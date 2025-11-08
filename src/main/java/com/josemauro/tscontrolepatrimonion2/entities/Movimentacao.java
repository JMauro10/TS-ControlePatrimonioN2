package com.josemauro.tscontrolepatrimonion2.entities;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Um Patrimonio pode ter muitas Movimentacoes.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patrimonio_id")
    private Patrimonio patrimonio;

    @Column(nullable = false)
    private Date dataMovimentacao;

    // Uma Localizacao pode ser origem de muitas Movimentacoes.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "de_localizacao_id")
    private Localizacao deLocalizacao;

    // Uma Localizacao pode ser destino de muitas Movimentacoes.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "para_localizacao_id")
    private Localizacao paraLocalizacao;

    // Uma Pessoa pode ser responsavel de origem muitas vezes.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "de_responsavel_id")
    private Pessoa deResponsavel;

    // Uma Pessoa pode ser responsavel de destino muitas vezes.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "para_responsavel_id")
    private Pessoa paraResponsavel;

    @Column(nullable = false)
    private String nota;

    // Uma Pessoa pode criar muitas Movimentacoes.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "criador_movimento_id")
    private Pessoa criadorMovimento;

    public Movimentacao() {
    }

    public Movimentacao(Long id, Patrimonio patrimonio, Date dataMovimentacao, Localizacao deLocalizacao,
            Localizacao paraLocalizacao, Pessoa deResponsavel, Pessoa paraResponsavel, String nota,
            Pessoa criadorMovimento) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.dataMovimentacao = dataMovimentacao;
        this.deLocalizacao = deLocalizacao;
        this.paraLocalizacao = paraLocalizacao;
        this.deResponsavel = deResponsavel;
        this.paraResponsavel = paraResponsavel;
        this.nota = nota;
        this.criadorMovimento = criadorMovimento;
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

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Localizacao getDeLocalizacao() {
        return deLocalizacao;
    }

    public void setDeLocalizacao(Localizacao deLocalizacao) {
        this.deLocalizacao = deLocalizacao;
    }

    public Localizacao getParaLocalizacao() {
        return paraLocalizacao;
    }

    public void setParaLocalizacao(Localizacao paraLocalizacao) {
        this.paraLocalizacao = paraLocalizacao;
    }

    public Pessoa getDeResponsavel() {
        return deResponsavel;
    }

    public void setDeResponsavel(Pessoa deResponsavel) {
        this.deResponsavel = deResponsavel;
    }

    public Pessoa getParaResponsavel() {
        return paraResponsavel;
    }

    public void setParaResponsavel(Pessoa paraResponsavel) {
        this.paraResponsavel = paraResponsavel;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Pessoa getCriadorMovimento() {
        return criadorMovimento;
    }

    public void setCriadorMovimento(Pessoa criadorMovimento) {
        this.criadorMovimento = criadorMovimento;
    }
    
}
