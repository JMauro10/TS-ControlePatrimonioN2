package com.josemauro.tscontrolepatrimonion2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.josemauro.tscontrolepatrimonion2.entities.Pessoa;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@CrossOrigin("*")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> listarPessoa() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    public Pessoa incluirPessoa(@RequestBody Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    @PutMapping
    public Pessoa alterarVeiculoByID(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void deletarPessoaById(@PathVariable Long id){
        pessoaRepository.deleteById(id);
    }
}
