package com.web2.biblioteca.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity getUsuario(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(usuarioId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao buscar usuário: " + e);
        }
    }


    @PostMapping()
    public ResponseEntity postUsuario(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao cadastrar usuário!" + e);
        }
    }

}
