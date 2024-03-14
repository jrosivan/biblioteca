package com.web2.biblioteca.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroMapper {

    public static Livro convertEntityToDto(LivroEntity livroEntity) {
        Livro livro = new Livro();
        livro.setAno(livroEntity.getAno());
        livro.setAutor(livroEntity.getAutor());
        livro.setTitulo(livroEntity.getTitulo());
        livro.setIsbn(livroEntity.getIsbn());
        livro.setGenero(livroEntity.getGenero());
        livro.setDescricao(livroEntity.getDescricao());
        livro.setQuantidade(livroEntity.getQuantidade());
        return livro;
    }

    public static List<Livro> convertEntityToDto(List<LivroEntity> livrosEntity) {
        List<Livro> livros = new ArrayList<>();
        for (LivroEntity livroEntity : livrosEntity) {
            livros.add(convertEntityToDto(livroEntity));
        }
        return livros;
    }


    public static LivroEntity convertToEntity(Livro livro) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setAno(livro.getAno());
        livroEntity.setAutor(livro.getAutor());
        livroEntity.setTitulo(livro.getTitulo());
        livroEntity.setIsbn(livro.getIsbn());
        livroEntity.setGenero(livro.getGenero());
        livroEntity.setDescricao(livro.getDescricao());
        livroEntity.setQuantidade(livro.getQuantidade());
        return livroEntity;
    }
    public static List<LivroEntity> convertToEntity(List<Livro> livros) {
        List<LivroEntity> livrosEntity = new ArrayList<>();
        for (Livro livro: livros) {
            livrosEntity.add(convertToEntity(livro));
        }
        return livrosEntity;
    }

}
