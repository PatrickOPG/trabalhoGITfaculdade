package com.unasp.trabalhoGITfaculdade.repositories;

import com.unasp.trabalhoGITfaculdade.entitys.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository
        extends JpaRepository<Curso, Integer> {
}