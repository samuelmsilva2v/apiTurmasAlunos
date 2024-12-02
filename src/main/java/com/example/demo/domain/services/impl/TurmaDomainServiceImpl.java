package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.TurmaDetailedResponseDto;
import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.models.dtos.TurmaResponseDto;
import com.example.demo.domain.models.entities.Turma;
import com.example.demo.domain.services.interfaces.TurmaDomainService;
import com.example.demo.infrastructure.repositories.TurmaRepository;

@Service
public class TurmaDomainServiceImpl implements TurmaDomainService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TurmaResponseDto cadastrarTurma(TurmaRequestDto request) {

		var turma = modelMapper.map(request, Turma.class);
		turma.setId(UUID.randomUUID());

		turmaRepository.save(turma);

		return modelMapper.map(turma, TurmaResponseDto.class);
	}

	@Override
	public TurmaDetailedResponseDto atualizarTurma(UUID id, TurmaRequestDto request) {

		var turma = turmaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
		
		turma.setNumero(request.getNumero());
		turma.setAnoLetivo(request.getAnoLetivo());
		
		turmaRepository.save(turma);

		return modelMapper.map(turma, TurmaDetailedResponseDto.class);
	}

	@Override
	public String excluirTurma(UUID id) {
		
		var turma = turmaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
		
		turmaRepository.delete(turma);
		
		return "Turma excluída com sucesso!";
	}

	@Override
	public TurmaDetailedResponseDto consultarTurmaPorId(UUID id) {
		
		var turma = turmaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Turma não encontrada."));
		
		return modelMapper.map(turma, TurmaDetailedResponseDto.class);
	}

	@Override
	public List<TurmaDetailedResponseDto> consultarTurmas() {
		
		return turmaRepository.findAll().stream()
	            .map(turma -> modelMapper.map(turma, TurmaDetailedResponseDto.class))
	            .collect(Collectors.toList());
	}

}
