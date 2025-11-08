package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.entities.Categoria;
import com.josemauro.tscontrolepatrimonion2.repository.LocalizacaoRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;


    @GetMapping
    public List<Movimentacao> listarMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Movimentacao> criar(@RequestBody Movimentacao movimentacao) {
        // Carregando cada entidade relacionada antes de salvar para que os dados nao retorne null
        if (movimentacao.getPatrimonio() != null && movimentacao.getPatrimonio().getId() != null) {
            movimentacao.setPatrimonio(
                    patrimonioRepository.findById(movimentacao.getPatrimonio().getId()).orElse(null)
            );
        }

        if (movimentacao.getDeLocalizacao() != null && movimentacao.getDeLocalizacao().getId() != null) {
            movimentacao.setDeLocalizacao(
                    localizacaoRepository.findById(movimentacao.getDeLocalizacao().getId()).orElse(null)
            );
        }

        if (movimentacao.getParaLocalizacao() != null && movimentacao.getParaLocalizacao().getId() != null) {
            movimentacao.setParaLocalizacao(
                    localizacaoRepository.findById(movimentacao.getParaLocalizacao().getId()).orElse(null)
            );
        }

        if (movimentacao.getDeResponsavel() != null && movimentacao.getDeResponsavel().getId() != null) {
            movimentacao.setDeResponsavel(
                    pessoaRepository.findById(movimentacao.getDeResponsavel().getId()).orElse(null)
            );
        }

        if (movimentacao.getParaResponsavel() != null && movimentacao.getParaResponsavel().getId() != null) {
            movimentacao.setParaResponsavel(
                    pessoaRepository.findById(movimentacao.getParaResponsavel().getId()).orElse(null)
            );
        }

        if (movimentacao.getCriadorMovimento() != null && movimentacao.getCriadorMovimento().getId() != null) {
            movimentacao.setCriadorMovimento(
                    pessoaRepository.findById(movimentacao.getCriadorMovimento().getId()).orElse(null)
            );
        }

        Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);
        return ResponseEntity.ok(movimentacaoSalva);
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
