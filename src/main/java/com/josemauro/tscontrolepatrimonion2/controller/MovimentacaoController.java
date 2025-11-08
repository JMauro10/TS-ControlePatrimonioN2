package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.josemauro.tscontrolepatrimonion2.entities.Movimentacao;
import com.josemauro.tscontrolepatrimonion2.repository.MovimentacaoRepository;

import java.util.List;

@RestController
@RequestMapping("/movimentacao")
@CrossOrigin("*")
public class MovimentacaoController {
    
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @GetMapping
    public List<Movimentacao> listarMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    @PostMapping
    public Movimentacao incluirMovimentacao(@RequestBody Movimentacao movimentacao){
        return movimentacaoRepository.save(movimentacao);
    }

    @PutMapping
    public Movimentacao alterarMovimentacaoByID(@RequestBody Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    @DeleteMapping("/{id}")
    public void deletarMovimentacaoById(@PathVariable Long id){
        movimentacaoRepository.deleteById(id);
    }
}
