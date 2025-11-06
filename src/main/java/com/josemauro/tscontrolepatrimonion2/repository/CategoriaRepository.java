package com.josemauro.tscontrolepatrimonion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josemauro.tscontrolepatrimonion2.entities.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{}
