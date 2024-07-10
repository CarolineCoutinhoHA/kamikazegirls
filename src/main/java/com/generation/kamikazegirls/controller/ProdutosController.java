package com.generation.kamikazegirls.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.generation.kamikazegirls.model.Produtos;
import com.generation.kamikazegirls.repository.ProdutosRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping
    public ResponseEntity<List<Produtos>> getAll() {
        List<Produtos> produtos = produtosRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produtos> getById(@PathVariable Long id) {
        Optional<Produtos> produto = produtosRepository.findById(id);
        return produto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
        List<Produtos> produtos = produtosRepository.findAllByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtosRepository.save(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produtos> put(@PathVariable Long id, @Valid @RequestBody Produtos produtoAtualizado) {
        Optional<Produtos> produtoOpt = produtosRepository.findById(id);
        if (produtoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoAtualizado.setId(id);
        Produtos produtoSalvo = produtosRepository.save(produtoAtualizado);
        return ResponseEntity.ok(produtoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        Optional<Produtos> produto = produtosRepository.findById(id);
        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        produtosRepository.deleteById(id);
    }
}


