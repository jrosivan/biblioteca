package com.web2.biblioteca.Livro;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro createLivro(Livro livro) {
        if (livroRepository.findByIsbn(livro.getIsbn()).isPresent()) {
            throw new RuntimeException("Livro com esse ISBN já está cadastrado!");
        }
        livroRepository.save(LivroMapper.convertToEntity(livro));
        return livro;

    }

    public Livro findById(Long livroId) {
        LivroEntity livroEntity = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não cadastrado! ID: " + livroId));
        return LivroMapper.convertEntityToDto(livroEntity);
    }

    public List<Livro> getAll() {
        return LivroMapper.convertEntityToDto(livroRepository.findAll());
    }

}
