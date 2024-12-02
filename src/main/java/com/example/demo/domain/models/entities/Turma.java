package com.example.demo.domain.models.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_turmas")
@Data
public class Turma {

	@Id
	private UUID id;
	private String numero;
	private Integer anoLetivo;
	
	@ManyToMany(mappedBy = "turmas")
	private List<Aluno> alunos;
}
