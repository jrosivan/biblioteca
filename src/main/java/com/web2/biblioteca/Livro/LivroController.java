package com.web2.biblioteca.Livro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping()
    public ResponseEntity<List<Livro>> getAllLivros(){
        return ResponseEntity.ok().body(livroService.getAll());
    }

    @GetMapping("/{livroId}")
    public ResponseEntity getLivro(@PathVariable Long livroId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(livroService.findById(livroId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao buscar livro: " + e);
        }
    }

    @PostMapping()
    public ResponseEntity postLivro(@RequestBody Livro livro) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(livroService.createLivro(livro));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao cadastrar livro: " + e);
        }
    }


}
