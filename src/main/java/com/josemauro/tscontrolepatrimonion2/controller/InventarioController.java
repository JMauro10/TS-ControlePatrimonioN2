package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Baixa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.josemauro.tscontrolepatrimonion2.entities.Inventario;
import com.josemauro.tscontrolepatrimonion2.repository.InventarioRepository;

import java.util.List;

@RestController
@RequestMapping("/inventario")
@CrossOrigin("*")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping
    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll();
    }

    @PostMapping
    public Inventario incluirInventario(@RequestBody Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    @PutMapping
    public Inventario alterarInventarioByID(@RequestBody Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    @DeleteMapping("/{id}")
    public void deletarInventarioById(@PathVariable Long id){
        inventarioRepository.deleteById(id);
    }
}
