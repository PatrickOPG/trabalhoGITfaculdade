package com.unasp.trabalhoGITfaculdade.controllers;



import com.unasp.trabalhoGITfaculdade.dtos.AlunoRequestDTO;
import com.unasp.trabalhoGITfaculdade.entitys.Aluno;
import com.unasp.trabalhoGITfaculdade.repositories.AlunoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    public AlunoController(
            AlunoRepository alunoRepository,
            CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    // CREATE: cadastrar aluno em um curso existente
    @PostMapping
    public ResponseEntity<?> criar(
            @RequestBody AlunoRequestDTO dados) {

        var cursoOpt = cursoRepository.findById(dados.idCurso());

        if (cursoOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Curso não encontrado.");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(dados.nome());
        aluno.setEmail(dados.email());
        aluno.setCurso(cursoOpt.get());

        return ResponseEntity.ok(alunoRepository.save(aluno));
    }

    // READ: listar alunos
    @GetMapping
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    // READ: consultar aluno por ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable Integer id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ: listar alunos de um curso
    @GetMapping("/curso/{idCurso}")
    public ResponseEntity<?> listarPorCurso(
            @PathVariable Integer idCurso) {

        if (!cursoRepository.existsById(idCurso)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                alunoRepository.findByCursoIdCurso(idCurso)
        );
    }

    // UPDATE: atualizar dados do aluno e seu curso
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Integer id,
            @RequestBody AlunoRequestDTO dados) {

        var alunoOpt = alunoRepository.findById(id);

        if (alunoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var cursoOpt = cursoRepository.findById(dados.idCurso());

        if (cursoOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Curso não encontrado.");
        }

        Aluno aluno = alunoOpt.get();
        aluno.setNome(dados.nome());
        aluno.setEmail(dados.email());
        aluno.setCurso(cursoOpt.get());

        return ResponseEntity.ok(alunoRepository.save(aluno));
    }

    // DELETE: excluir aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        if (!alunoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        alunoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}