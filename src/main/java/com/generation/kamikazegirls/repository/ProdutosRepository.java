package com.generation.kamikazegirls.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.generation.kamikazegirls.model.Produtos;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    List<Produtos> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
