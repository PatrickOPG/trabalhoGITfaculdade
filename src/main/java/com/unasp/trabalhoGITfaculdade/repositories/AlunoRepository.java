package com.unasp.trabalhoGITfaculdade.repositories;

import com.unasp.trabalhoGITfaculdade.entitys.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlunoRepository
        extends JpaRepository<Aluno, Integer> {

    List<Aluno> findByCursoIdCurso(Integer idCurso);
}