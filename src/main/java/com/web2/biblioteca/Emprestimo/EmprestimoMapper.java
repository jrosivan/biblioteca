package com.web2.biblioteca.Emprestimo;

import com.web2.biblioteca.Livro.LivroEntity;
import com.web2.biblioteca.Livro.LivroRepository;
import com.web2.biblioteca.Usuario.UsuarioEntity;
import com.web2.biblioteca.Usuario.UsuarioRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoMapper {

    LivroRepository livroRepository;
    UsuarioRepository usuarioRepository;

    public EmprestimoMapper(LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public static Emprestimo convertEntityToDto(EmprestimoEntity emprestimoEntity) {
        Emprestimo emprestimo = new Emprestimo();
        BeanUtils.copyProperties(emprestimoEntity, emprestimo);
        emprestimo.setUsuario_id(emprestimoEntity.getUsuario().getId());
        emprestimo.setLivro_id(emprestimoEntity.getLivro().getId());
        emprestimo.setNomeUsuario(emprestimoEntity.getUsuario().getNome());
        emprestimo.setNomeLivro(emprestimoEntity.getLivro().getTitulo());
        return emprestimo;
    }

    public static List<Emprestimo> convertEntityToDto(List<EmprestimoEntity> emprestimoEntityList) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        for (EmprestimoEntity emprestimoEntity : emprestimoEntityList) {
            emprestimos.add(convertEntityToDto(emprestimoEntity));
        }
        return emprestimos;
    }


    public static EmprestimoEntity convertDtoToEntity(Emprestimo emprestimo,
                                                      UsuarioEntity usuarioEntity,
                                                      LivroEntity livroEntity) {
        EmprestimoEntity emprestimoEntity = new EmprestimoEntity();
        BeanUtils.copyProperties(emprestimo, emprestimoEntity);
        emprestimoEntity.setUsuario(usuarioEntity);
        emprestimoEntity.setLivro(livroEntity);
        return emprestimoEntity;
    }

    public List<EmprestimoEntity> convertDtoToEntity(List<Emprestimo> emprestimoList,
                                                     UsuarioEntity usuarioEntity,
                                                     LivroEntity livroEntity) {
        List<EmprestimoEntity> emprestimoEntityList = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimoList) {
            emprestimoEntityList.add(convertDtoToEntity(emprestimo, usuarioEntity, livroEntity));
        }
        return emprestimoEntityList;
    }

}
