package com.web2.biblioteca.Usuario;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário com esse email já está cadastrado!");
        }
        usuarioRepository.save(UsuarioMapper.convertToEntity(usuario));
        return  usuario;
    }

    public Usuario findById(Long usuarioId) {
        UsuarioEntity livroEntity = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não cadastrado! ID: " + usuarioId));
        return UsuarioMapper.convertEntityToDto(livroEntity);
    }

    public List<Usuario> getAll() {
        return UsuarioMapper.convertEntityToDto(usuarioRepository.findAll());
    }

}
