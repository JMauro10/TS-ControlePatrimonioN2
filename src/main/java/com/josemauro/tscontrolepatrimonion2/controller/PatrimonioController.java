package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Movimentacao;
import com.josemauro.tscontrolepatrimonion2.entities.Patrimonio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;

import java.util.List;

@RestController
@RequestMapping("/patrimonio")
@CrossOrigin("*")
public class PatrimonioController {
    
    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @GetMapping
    public List<Patrimonio> listarPatrimonio() {
        return patrimonioRepository.findAll();
    }

    @PostMapping
    public Patrimonio incluirPatrimonio(@RequestBody Patrimonio patrimonio){
        return patrimonioRepository.save(patrimonio);
    }

    @PutMapping
    public Patrimonio alterarPatrimonioByID(@RequestBody Patrimonio patrimonio) {
        return patrimonioRepository.save(patrimonio);
    }

    @DeleteMapping("/{id}")
    public void deletarPatrimonioById(@PathVariable Long id){
        patrimonioRepository.deleteById(id);
    }
}
