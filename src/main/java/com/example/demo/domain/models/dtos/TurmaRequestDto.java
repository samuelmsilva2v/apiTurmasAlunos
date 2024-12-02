package com.example.demo.domain.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TurmaRequestDto {

	@NotBlank(message = "Número da turma é obrigatório.")
	@Size(max = 4, message = "Número pode ter no máximo 4 caracteres.")
	private String numero;

	@NotNull(message = "Ano letivo é obrigatório.")
	private Integer anoLetivo;
}
