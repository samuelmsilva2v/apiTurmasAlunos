package com.example.demo.domain.models.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoRequestDto {

	@NotBlank(message = "Nome é obrigatório.")
    @Size(max = 100, message = "Nome pode ter no máximo 100 caracteres.")
	private String nome;
	
	@NotBlank(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres.")
    private String cpf;
	
	@NotBlank(message = "O E-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    @Size(max = 100, message = "E-mail pode ter no máximo 100 caracteres.")
    private String email;
	
	private List<UUID> turmasIds;
}
