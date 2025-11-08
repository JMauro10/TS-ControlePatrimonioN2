package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Patrimonio;
import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.josemauro.tscontrolepatrimonion2.entities.Baixa;
import com.josemauro.tscontrolepatrimonion2.repository.BaixaRepository;

import java.util.List;

@RestController
@RequestMapping("/baixa")
@CrossOrigin("*")
public class BaixaController {

    @Autowired
    private BaixaRepository baixaRepository;

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Baixa> listarBaixa() {
        return baixaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Baixa> incluirBaixa(@RequestBody Baixa baixa) {
        // Carregando cada entidade relacionada antes de salvar para que os dados não retorne null
        if (baixa.getPatrimonio() != null && baixa.getPatrimonio().getId() != null) {
            baixa.setPatrimonio(
                    patrimonioRepository.findById(baixa.getPatrimonio().getId()).orElse(null)
            );
        }

        if (baixa.getAutorizadoPor() != null && baixa.getAutorizadoPor().getId() != null) {
            baixa.setAutorizadoPor(
                    pessoaRepository.findById(baixa.getAutorizadoPor().getId()).orElse(null)
            );
        }

        Baixa baixaSalva = baixaRepository.save(baixa);
        return ResponseEntity.ok(baixaSalva);
    }

    @PutMapping
    public Baixa alterarBaixaByID(@RequestBody Baixa baixa) {
        return baixaRepository.save(baixa);
    }

    @DeleteMapping("/{id}")
    public void deletarBaixaById(@PathVariable Long id){
        baixaRepository.deleteById(id);
    }
    
}
