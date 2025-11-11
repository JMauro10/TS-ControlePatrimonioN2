package com.josemauro.tscontrolepatrimonion2.controller;

import com.josemauro.tscontrolepatrimonion2.repository.LocalizacaoRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PatrimonioRepository;
import com.josemauro.tscontrolepatrimonion2.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;


    @GetMapping
    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Inventario> incluirInventario(@RequestBody Inventario inventario) {
        // Carregando cada entidade relacionada antes de salvar para que os dados não retorne null
        if (inventario.getPatrimonio() != null && inventario.getPatrimonio().getId() != null) {
            inventario.setPatrimonio(
                    patrimonioRepository.findById(inventario.getPatrimonio().getId()).orElse(null)
            );
        }

        if (inventario.getLocalizacaoFisica() != null && inventario.getLocalizacaoFisica().getId() != null) {
            inventario.setLocalizacaoFisica(
                    localizacaoRepository.findById(inventario.getLocalizacaoFisica().getId()).orElse(null)
            );
        }

        if (inventario.getAuditor() != null && inventario.getAuditor().getId() != null) {
            inventario.setAuditor(
                    pessoaRepository.findById(inventario.getAuditor().getId()).orElse(null)
            );
        }

        Inventario inventarioSalvo = inventarioRepository.save(inventario);
        return ResponseEntity.ok(inventarioSalvo);
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
