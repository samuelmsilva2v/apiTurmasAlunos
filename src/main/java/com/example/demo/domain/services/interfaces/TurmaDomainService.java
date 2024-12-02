package com.example.demo.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

import com.example.demo.domain.models.dtos.TurmaDetailedResponseDto;
import com.example.demo.domain.models.dtos.TurmaRequestDto;

public interface TurmaDomainService {

	TurmaDetailedResponseDto cadastrarTurma(TurmaRequestDto request);
	
	TurmaDetailedResponseDto atualizarTurma(UUID id, TurmaRequestDto request);
	
	String excluirTurma(UUID id);
	
	TurmaDetailedResponseDto consultarTurmaPorId(UUID id);
	
	List<TurmaDetailedResponseDto> consultarTurmas();
}
