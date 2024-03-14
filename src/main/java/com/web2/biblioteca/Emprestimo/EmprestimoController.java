package com.web2.biblioteca.Emprestimo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;  // este estava sendo sobreposto pelo do swagger...

// ** Está anulando a anotação @RequestBody do Spring Boot, resultando em valores null nos parâmetros do JSON:
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
// ==> Necessário injetar diretamente na chamada

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping()
    public ResponseEntity<List<Emprestimo>> getAllEmprestimos(){
        return ResponseEntity.ok().body(emprestimoService.getAll());
    }

    @GetMapping("/{emprestimoId}")
    public ResponseEntity getEmprestimo(@PathVariable Long emprestimoId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.findById(emprestimoId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao buscar empréstimo: " + e);
        }

    }

    @PostMapping()
    public ResponseEntity postEmprestimo(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "<html>JSON com os dados para empréstimo.<br/>" +
                            "<b>OBS</b>: Caso não fornecido 'dataEmprestimo' e 'prazoDevolucao', os mesmo serão calculados!</html>")
            @RequestBody Emprestimo emprestimo) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoService.createEmprestimo(emprestimo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao cadastrar empréstimo!" + e);
        }
    }

    @PutMapping("/{emprestimoId}/devolve")
    public ResponseEntity putDevolucao(
            @Parameter(description = "ID do empréstimo: Obrigatório", required = true) @PathVariable Long emprestimoId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "<html>Json com a data da devolução do empréstimo (opcional).<br/><b>OBS</b>: Caso não fornecido, assume a data atual!</html>",
            content = @Content(mediaType = "application/json",
            schema = @Schema(example = "{\"dataDevolucao\": \"2024-03-15T12:01:01\"}")))
            @RequestBody Optional<Emprestimo> emprestimoOptional) {
        try {
            return ResponseEntity.ok().body(emprestimoService.putDevolucao(emprestimoId, emprestimoOptional));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Erro ao devolver empréstimo!" + e);
        }
    }

}
