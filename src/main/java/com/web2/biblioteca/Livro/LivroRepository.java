package com.web2.biblioteca.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    Optional<Object> findByIsbn(Integer isbn);

}
