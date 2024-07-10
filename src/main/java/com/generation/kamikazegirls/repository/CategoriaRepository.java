package com.generation.kamikazegirls.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.kamikazegirls.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);

}

