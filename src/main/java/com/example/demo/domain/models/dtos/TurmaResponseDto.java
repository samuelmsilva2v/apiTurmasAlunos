package com.example.demo.domain.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class TurmaResponseDto {

	private UUID id;
	private String numero;
	private Integer anoLetivo;
}
