package com.josemauro.tscontrolepatrimonion2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import java.util.List;


@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long>{
    List<Localizacao> findByNome(String nome);
}
