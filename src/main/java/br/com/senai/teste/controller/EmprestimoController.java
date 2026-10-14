package br.com.senai.teste.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.teste.dto.EmprestimoRequest;
import br.com.senai.teste.model.Emprestimo;
import br.com.senai.teste.service.EmprestimoService;

@RestController 
@RequestMapping ("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping 
    public ResponseEntity<Emprestimo> cadastrar (
        @RequestBody EmprestimoRequest dados) {

            Integer alunoId = dados.getAlunoId();
            Integer livroId = dados.getLivroId();

            if(alunoId == null || livroId == null) {
                return ResponseEntity.badRequest().build();
            }

            Optional<Emprestimo> emprestimo = emprestimoService.cadastrar(alunoId, livroId);

            if (emprestimo.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(emprestimo.get());
        }
    
}
