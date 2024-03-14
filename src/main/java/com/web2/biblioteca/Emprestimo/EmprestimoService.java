package com.web2.biblioteca.Emprestimo;

import com.web2.biblioteca.Livro.LivroEntity;
import com.web2.biblioteca.Livro.LivroRepository;
import com.web2.biblioteca.Usuario.UsuarioEntity;
import com.web2.biblioteca.Usuario.UsuarioRepository;
import com.web2.biblioteca.Utils.Constants;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static java.time.LocalDate.now;

@Service
public class EmprestimoService {

    EmprestimoRepository emprestimoRepository;
    LivroRepository livroRepository;
    UsuarioRepository usuarioRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LivroRepository livroRepository,
                             UsuarioRepository usuarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Emprestimo> getAll() {
        return EmprestimoMapper.convertEntityToDto(emprestimoRepository.findAll());
    }

    public Emprestimo findById(Long emprestimoId) {
        EmprestimoEntity emprestimoEntity = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Empréstimo não cadastrado! ID: " + emprestimoId));
        return EmprestimoMapper.convertEntityToDto(emprestimoEntity);
    }

    public Emprestimo createEmprestimo(Emprestimo emprestimo) {

        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(emprestimo.getUsuario_id());
        if (!usuarioEntityOptional.isPresent()) {
            throw new RuntimeException("Usuário não cadastrado!");
        }

        UsuarioEntity usuarioEntity = usuarioEntityOptional.get();

        Optional<LivroEntity> livroEntityOptional = livroRepository.findById(emprestimo.getLivro_id());

        if (!livroEntityOptional.isPresent()) {
            throw new RuntimeException("Livro não cadastrado!");
        }

        LivroEntity livroEntity = livroEntityOptional.get();

        if (livroEntity.getQuantidade() <= 0) {
            throw new RuntimeException("Livro está sem estoque para aluguel!");
        }

        if (emprestimo.getDataEmprestimo() == null) {
            emprestimo.setDataEmprestimo(now());
        }

        if (emprestimo.getPrazoDevolucao() == null) {
            emprestimo.setPrazoDevolucao(emprestimo.getDataEmprestimo().plusDays(Constants.DIAS_PRAZO_PARA_DEVOLUCAO));
        }

        EmprestimoEntity emprestimoEntity = EmprestimoMapper.convertDtoToEntity(emprestimo, usuarioEntity, livroEntity);
        emprestimoRepository.save(emprestimoEntity);

        livroEntity.setQuantidade(livroEntity.getQuantidade()-1);
        livroRepository.save(livroEntity);

        return EmprestimoMapper.convertEntityToDto(emprestimoEntity);

    }

    public Emprestimo putDevolucao(Long emprestimoId, Optional<Emprestimo> emprestimoOptional) {

        Emprestimo emprestimo = emprestimoOptional.orElseGet(() -> new Emprestimo());
        if (emprestimo.getDataDevolucao() == null) {
            emprestimo.setDataDevolucao(now());
        }

        Optional<EmprestimoEntity> emprestimoEntityOptional = emprestimoRepository.findById(emprestimoId);
        if (!emprestimoEntityOptional.isPresent()) {
            throw new RuntimeException("Empréstimo não cadastrado!");
        }
        if  (emprestimoEntityOptional.get().getDataDevolucao() != null ) {
            throw new RuntimeException("Empréstimo já devolvido: " + emprestimoEntityOptional.get().getDataDevolucao());
        }
        EmprestimoEntity emprestimoEntity = emprestimoEntityOptional.get();
        emprestimoEntity.setDataDevolucao(emprestimo.getDataDevolucao());

        if (emprestimoEntity.getDataDevolucao().isBefore(emprestimoEntity.getDataEmprestimo())) {
            throw new RuntimeException("Data de Devolução menor que a data de empréstimo!");
        }

        calcularMulta(emprestimoEntity);

        emprestimoRepository.save(emprestimoEntity);

        LivroEntity livroEntity = emprestimoEntity.getLivro();
        livroEntity.setQuantidade(livroEntity.getQuantidade()+1);

        livroRepository.save(livroEntity);

        return EmprestimoMapper.convertEntityToDto(emprestimoEntity);
    }

    private void calcularMulta(EmprestimoEntity emprestimoEntity) {
        if (emprestimoEntity.getDataDevolucao().isAfter(emprestimoEntity.getPrazoDevolucao())) {
            BigDecimal dias = BigDecimal.valueOf(ChronoUnit.DAYS.between(emprestimoEntity.getPrazoDevolucao(), emprestimoEntity.getDataDevolucao()));
            BigDecimal valorMulta = dias.multiply(Constants.MULTA_DIARIA_POR_ATRASO);
            emprestimoEntity.setValorMulta(valorMulta);
            emprestimoEntity.setObservacoes(String.format(
               "Livro com %s dias de atraso. Valor da multa = R$ %s (diária = R$ %s)",dias, valorMulta, Constants.MULTA_DIARIA_POR_ATRASO
            ));
        } else {
            emprestimoEntity.setValorMulta(BigDecimal.ZERO);
            emprestimoEntity.setObservacoes("Devolução no prazo!");
        }
    }

}
