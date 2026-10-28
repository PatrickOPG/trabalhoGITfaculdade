package com.unasp.trabalhoGITfaculdade.dtos;

public record AlunoRequestDTO(
        String nome,
        String email,
        Integer idCurso
) {
}