package com.web2.biblioteca.Emprestimo;

import com.web2.biblioteca.Livro.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<EmprestimoEntity, Long> {

    Optional<LivroEntity> findByLivro(LivroEntity livro);

}
