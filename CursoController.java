package com.unasp.trabalhoGITfaculdade.controllers;

import com.unasp.trabalhoGITfaculdade.entitys.Curso;
import com.unasp.trabalhoGITfaculdade.repositories.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoRepository repository;

    public CursoController(CursoRepository repository) {
        this.repository = repository;
    }

    // CREATE: cadastrar curso
    @PostMapping
    public Curso criar(@RequestBody Curso curso) {
        return repository.save(curso);
    }

    // READ: listar cursos
    @GetMapping
    public List<Curso> listar() {
        return repository.findAll();
    }

    // READ: consultar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE: atualizar curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(
            @PathVariable Integer id,
            @RequestBody Curso dados) {

        return repository.findById(id).map(curso -> {
            curso.setNome(dados.getNome());
            return ResponseEntity.ok(repository.save(curso));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE: excluir curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}