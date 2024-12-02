package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.TurmaDetailedResponseDto;
import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.services.interfaces.TurmaDomainService;

@Service
public class TurmaDomainServiceImpl implements TurmaDomainService {

	@Override
	public TurmaDetailedResponseDto cadastrarTurma(TurmaRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TurmaDetailedResponseDto atualizarTurma(UUID id, TurmaRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluirTurma(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TurmaDetailedResponseDto consultarTurmaPorId(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TurmaDetailedResponseDto> consultarTurmas() {
		// TODO Auto-generated method stub
		return null;
	}

}
