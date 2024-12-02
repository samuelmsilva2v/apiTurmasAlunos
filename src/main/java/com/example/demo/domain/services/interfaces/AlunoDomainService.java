package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.models.dtos.AlunoDetailedResponseDto;
import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;

public interface AlunoDomainService {

	AlunoResponseDto cadastrarAluno(AlunoRequestDto request);
	
	AlunoDetailedResponseDto atualizarAluno(UUID id, AlunoRequestDto request);
	
	String excluirAluno(UUID id);
	
	AlunoResponseDto consultarAlunoPorId(UUID id);
	
	List<AlunoResponseDto> consultarAlunos();
}
