package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Localizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.josemauro.tscontrolepatrimonion2.entities.Categoria;
import com.josemauro.tscontrolepatrimonion2.repository.CategoriaRepository;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listarCategoria() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria incluirCategoria(@RequestBody Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    @PutMapping
    public Categoria alterarCategoriaByID(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void deletarCategoriaById(@PathVariable Long id){
        categoriaRepository.deleteById(id);
    }
}
