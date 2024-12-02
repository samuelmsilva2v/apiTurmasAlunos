package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.models.dtos.AlunoDetailedResponseDto;
import com.example.demo.domain.models.dtos.AlunoRequestDto;

public interface AlunoDomainService {

	AlunoDetailedResponseDto cadastrarAluno(AlunoRequestDto request);
	
	AlunoDetailedResponseDto atualizarAluno(UUID id, AlunoRequestDto request);
	
	String excluirAluno(UUID id);
	
	AlunoDetailedResponseDto consultarAlunoPorId(UUID id);
	
	List<AlunoDetailedResponseDto> consultarAlunos();
}
