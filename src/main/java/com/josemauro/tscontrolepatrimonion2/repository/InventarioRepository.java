package com.josemauro.tscontrolepatrimonion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.josemauro.tscontrolepatrimonion2.entities.Inventario;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository  extends JpaRepository<Inventario, Long>{}
