package com.example.demo.domain.models.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_alunos")
@Data
public class Aluno {

	@Id
	private UUID id;
	private String nome;
	private String cpf;
	private String email;
	
	@ManyToMany
	private List<Turma> turmas;
}
