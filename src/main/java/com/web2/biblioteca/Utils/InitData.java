package com.web2.biblioteca.Utils;

import com.web2.biblioteca.Emprestimo.EmprestimoEntity;
import com.web2.biblioteca.Emprestimo.EmprestimoRepository;
import com.web2.biblioteca.Emprestimo.EmprestimoService;
import com.web2.biblioteca.Livro.LivroEntity;
import com.web2.biblioteca.Livro.LivroRepository;
import com.web2.biblioteca.Livro.LivroService;
import com.web2.biblioteca.Usuario.UsuarioEntity;
import com.web2.biblioteca.Usuario.UsuarioRepository;
import com.web2.biblioteca.Usuario.UsuarioService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InitData {

    EmprestimoService emprestimoService;
    EmprestimoRepository emprestimoRepository;
    LivroService livroService;
    LivroRepository livroRepository;
    UsuarioService usuarioService;
    UsuarioRepository usuarioRepository;

    public InitData(EmprestimoService emprestimoService, EmprestimoRepository emprestimoRepository,
                    LivroService livroService, LivroRepository livroRepository,
                    UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.emprestimoService = emprestimoService;
        this.emprestimoRepository = emprestimoRepository;
        this.livroService = livroService;
        this.livroRepository = livroRepository;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }


    @PostConstruct
    public void initData() {

        if (livroService.getAll().isEmpty() ) {
            // LIVROS;;;;
            LivroEntity livro1 = new LivroEntity();
            livro1.setAutor("AUTOR DE LIVRO 0001");
            livro1.setTitulo("A CASA DE VIDRO");
            livro1.setGenero("SUSPENSE");
            livro1.setIsbn(123456);
            livro1.setAno(2010);
            livro1.setDescricao("LIVRO SOBRE ALGUMA COISA QUE NEM FAÇO IDÉIA");
            livro1.setQuantidade(5);
            livroRepository.save(livro1);

            LivroEntity livro2 = new LivroEntity();
            livro2.setAutor("AUTOR DE LIVRO NÚMERO 0002");
            livro2.setTitulo("ÉRAMOS SEIS");
            livro2.setGenero("DRAMA");
            livro2.setIsbn(333456);
            livro2.setAno(2001);
            livro2.setDescricao("OUTRO lIVRO COM MUITAS PALAVRAS");
            livro2.setQuantidade(3);
            livroRepository.save(livro2);

            LivroEntity livro3 = new LivroEntity();
            livro3.setAutor("AUTOR DE LIVRO NÚMERO 0003");
            livro3.setTitulo("VIAGEM AO CENTRO DA TERRA");
            livro3.setGenero("FICÇÃO");
            livro3.setIsbn(777456);
            livro3.setAno(2001);
            livro3.setDescricao("LIVRO COM PESSOAS FAZENDO UMA VIAGEM AO CENTRO DA TERRA");
            livro3.setQuantidade(7);
            livroRepository.save(livro3);
        }

        if (usuarioService.getAll().isEmpty() ) {
            // USUÁRIOS;;;;
            UsuarioEntity usuarioEntity1 = new UsuarioEntity();
            usuarioEntity1.setNome("BOB CAT");
            usuarioEntity1.setEmail("bobcat@email.com");
            usuarioEntity1.setEndereco("Rua São José, 1295, Centro, Brasília/DF");
            usuarioEntity1.setContato("(61) 9.9876-5422");
            usuarioRepository.save(usuarioEntity1);

            UsuarioEntity usuarioEntity2 = new UsuarioEntity();
            usuarioEntity2.setNome("DINO DA SILVA SAURO");
            usuarioEntity2.setEmail("dinosilva@email.com");
            usuarioEntity2.setEndereco("Avenida Central, S/N, Agulha, Brasília/DF");
            usuarioEntity2.setContato("(61) 9.9876-1234");
            usuarioRepository.save(usuarioEntity2);
        }


        if (emprestimoService.getAll().isEmpty() ) {
            // EMPRÉSTIMOS;;;;
//            EmprestimoEntity emprestimoEntity1 = new EmprestimoEntity();
//            emprestimoEntity1.setUsuario();
//            emprestimoEntity1.setLivro();
//            emprestimoEntity1.setDataEmprestimo();
//            emprestimoEntity1.setPrazoDevolucao();
//            emprestimoEntity1.setDataDevolucao();
//


        }

    }

}
