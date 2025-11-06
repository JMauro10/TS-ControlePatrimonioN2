package com.josemauro.tscontrolepatrimonion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josemauro.tscontrolepatrimonion2.entities.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long>{}
