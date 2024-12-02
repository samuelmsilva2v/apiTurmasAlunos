package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.AlunoDetailedResponseDto;
import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.services.interfaces.AlunoDomainService;

@Service
public class AlunoDomainServiceImpl implements AlunoDomainService {

	@Override
	public AlunoDetailedResponseDto cadastrarAluno(AlunoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlunoDetailedResponseDto atualizarAluno(UUID id, AlunoRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluirAluno(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlunoDetailedResponseDto consultarAlunoPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AlunoDetailedResponseDto> consultarAlunos() {
		// TODO Auto-generated method stub
		return null;
	}

}
