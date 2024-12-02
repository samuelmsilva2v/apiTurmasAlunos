package com.example.demo.domain.services.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.models.dtos.AlunoDetailedResponseDto;
import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;
import com.example.demo.domain.models.entities.Aluno;
import com.example.demo.domain.models.entities.Turma;
import com.example.demo.domain.services.interfaces.AlunoDomainService;
import com.example.demo.infrastructure.repositories.AlunoRepository;
import com.example.demo.infrastructure.repositories.TurmaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoDomainServiceImpl implements AlunoDomainService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AlunoResponseDto cadastrarAluno(AlunoRequestDto request) {

		// Validar se CPF ou e-mail já estão cadastrados
		if (alunoRepository.existsByCpf(request.getCpf())) {
			throw new IllegalArgumentException("CPF já cadastrado.");
		}

		if (alunoRepository.existsByEmail(request.getEmail())) {
			throw new IllegalArgumentException("E-mail já cadastrado.");
		}

		// Recuperar todas as turmas pelos IDs fornecidos no request
		List<Turma> turmas = turmaRepository.findAllById(request.getTurmasIds());

		// Validar se há turmar associadas
		if (turmas.isEmpty())
			throw new IllegalArgumentException("O aluno deve estar associado a pelo menos uma turma.");

		// Validar regras de negócio
		for (Turma turma : turmas) {
			// Verificar se a turma já possui o aluno
			if (turma.getAlunos().stream().anyMatch(a -> a.getCpf().equals(request.getCpf()))) {
				throw new IllegalArgumentException("O aluno já está matriculado na turma: " + turma.getNumero());
			}

			// Verificar se a turma excede o limite de 5 alunos
			if (turma.getAlunos().size() >= 5) {
				throw new IllegalArgumentException("A turma " + turma.getNumero() + " já atingiu o limite de alunos.");
			}
		}

		var aluno = modelMapper.map(request, Aluno.class);
		aluno.setId(UUID.randomUUID());
		aluno.setTurmas(turmas);

		alunoRepository.save(aluno);

		turmas.forEach(turma -> turma.getAlunos().add(aluno));
		turmaRepository.saveAll(turmas);

		return modelMapper.map(aluno, AlunoResponseDto.class);
	}

	@Override
	public AlunoDetailedResponseDto atualizarAluno(UUID id, AlunoRequestDto request) {

		// Buscar aluno no banco de dados através do ID
		var aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado."));

		// Validar se o CPF pertence a outro aluno
		if (alunoRepository.existsByCpf(request.getCpf()) && !aluno.getCpf().equals(request.getCpf())) {
			throw new IllegalArgumentException("CPF já cadastrado para outro aluno.");
		}

		// Validar se o e-mail pertence a outro aluno
		if (alunoRepository.existsByEmail(request.getEmail()) && !aluno.getEmail().equals(request.getEmail())) {
			throw new IllegalArgumentException("E-mail já cadastrado para outro aluno.");
		}

		// Atualizar os dados do aluno
		aluno.setNome(request.getNome());
		aluno.setCpf(request.getCpf());
		aluno.setEmail(request.getEmail());

		List<Turma> turmas = turmaRepository.findAllById(request.getTurmasIds());

		// Validar regras de negócio para cada turma
		for (Turma turma : turmas) {
			// Verificar se a turma excede o limite de 5 alunos
			if (!turma.getAlunos().contains(aluno) && turma.getAlunos().size() >= 5) {
				throw new IllegalArgumentException(
						"A turma " + turma.getNumero() + " já atingiu o limite de 5 alunos.");
			}
		}

		aluno.setTurmas(turmas);

		alunoRepository.save(aluno);

		return modelMapper.map(aluno, AlunoDetailedResponseDto.class);
	}

	@Override
	public String excluirAluno(UUID id) {

		var aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado."));

		if (!aluno.getTurmas().isEmpty()) {
			throw new IllegalArgumentException(
					"O aluno não pode ser excluído, pois está associado a uma ou mais turmas.");
		}

		alunoRepository.delete(aluno);

		return "Aluno excluído com sucesso!";
	}

	@Override
	public AlunoResponseDto consultarAlunoPorId(UUID id) {

		var aluno = alunoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado."));

		return modelMapper.map(aluno, AlunoResponseDto.class);
	}

	@Override
	public List<AlunoResponseDto> consultarAlunos() {

		var alunos = alunoRepository.findAll();

		return alunos.stream().map(aluno -> {
			var response = modelMapper.map(aluno, AlunoResponseDto.class);

			return response;
		}).toList();
	}
}
