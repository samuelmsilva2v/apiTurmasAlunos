package com.example.demo.domain.models.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class TurmaDetailedResponseDto {

	private UUID id;
	private String numero;
	private Integer anoLetivo;
	private List<AlunoResponseDto> alunos;
}
