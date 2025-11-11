package com.josemauro.tscontrolepatrimonion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import java.util.List;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
