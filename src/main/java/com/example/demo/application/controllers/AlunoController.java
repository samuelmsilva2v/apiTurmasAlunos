package com.example.demo.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.models.dtos.AlunoDetailedResponseDto;
import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;
import com.example.demo.domain.services.interfaces.AlunoDomainService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

	@Autowired
	private AlunoDomainService alunoDomainService;

	@PostMapping
	public AlunoResponseDto post(@RequestBody @Valid AlunoRequestDto request) {
		return alunoDomainService.cadastrarAluno(request);
	}

	@PutMapping("{id}")
	public AlunoDetailedResponseDto post(@PathVariable UUID id, @RequestBody @Valid AlunoRequestDto request) {
		return alunoDomainService.atualizarAluno(id, request);
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) {
		return alunoDomainService.excluirAluno(id);
	}

	@GetMapping("{id}")
	public AlunoResponseDto getById(@PathVariable UUID id) {
		return alunoDomainService.consultarAlunoPorId(id);
	}

	@GetMapping
	public List<AlunoResponseDto> getAll() {
		return alunoDomainService.consultarAlunos();
	}
}
