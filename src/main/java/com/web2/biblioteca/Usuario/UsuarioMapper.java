package com.web2.biblioteca.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapper {

    public static Usuario convertEntityToDto(UsuarioEntity usuarioEntity) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioEntity.getNome());
        usuario.setEndereco(usuarioEntity.getEndereco());
        usuario.setContato(usuarioEntity.getContato());
        usuario.setEmail(usuarioEntity.getEmail());
        return usuario;
    }

    public static List<Usuario> convertEntityToDto(List<UsuarioEntity> usuariosEntity) {
        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioEntity usuarioEntity : usuariosEntity) {
            usuarios.add(convertEntityToDto(usuarioEntity));
        }
        return usuarios;
    }


    public static UsuarioEntity convertToEntity(Usuario usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEndereco(usuario.getEndereco());
        usuarioEntity.setContato(usuario.getContato());
        usuarioEntity.setEmail(usuario.getEmail());
        return usuarioEntity;
    }
    public static List<UsuarioEntity> convertToEntity(List<Usuario> usuarios) {
        List<UsuarioEntity> usuariosEntity = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosEntity.add(convertToEntity(usuario));
        }
        return usuariosEntity;
    }

}
