package com.josemauro.tscontrolepatrimonion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josemauro.tscontrolepatrimonion2.entities.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{}
