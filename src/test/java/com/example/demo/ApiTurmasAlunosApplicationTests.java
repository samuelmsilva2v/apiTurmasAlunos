package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.domain.models.dtos.AlunoRequestDto;
import com.example.demo.domain.models.dtos.AlunoResponseDto;
import com.example.demo.domain.models.dtos.TurmaRequestDto;
import com.example.demo.domain.models.dtos.TurmaResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApiTurmasAlunosApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static UUID turmaId;

	private static UUID alunoId;

	@Test
	@Order(1)
	void cadastrarTurmaTest() throws Exception {

		var faker = new Faker(Locale.forLanguageTag("pt-BR"));

		var request = new TurmaRequestDto();
		request.setNumero(faker.number().digits(4));
		request.setAnoLetivo(faker.number().randomDigit());

		var result = mockMvc.perform(
				post("/api/turmas").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, TurmaResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(response.getNumero(), request.getNumero());
		assertEquals(response.getAnoLetivo(), request.getAnoLetivo());

		turmaId = response.getId();
	}

	@Test
	@Order(2)
	void consultarTurmaPorIdTest() throws Exception {

		var result = mockMvc.perform(get("/api/turmas/" + turmaId).contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, TurmaResponseDto.class);

		assertEquals(response.getId(), turmaId);
		assertNotNull(response.getNumero());
		assertNotNull(response.getAnoLetivo());
	}

	@Test
	@Order(3)
	void atualizarTurmaTest() throws Exception {

		var faker = new Faker(Locale.forLanguageTag("pt-BR"));

		var request = new TurmaRequestDto();
		request.setNumero(faker.number().digits(4));
		request.setAnoLetivo(faker.number().randomDigit());

		var result = mockMvc.perform(put("/api/turmas/" + turmaId).contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, TurmaResponseDto.class);

		assertEquals(response.getId(), turmaId);
		assertEquals(response.getNumero(), request.getNumero());
		assertEquals(response.getAnoLetivo(), request.getAnoLetivo());
	}

	@Test
	@Order(4)
	void cadastrarAlunoTest() throws Exception {

		var faker = new Faker(Locale.forLanguageTag("pt-BR"));

		var request = new AlunoRequestDto();
		request.setNome(faker.name().fullName());
		request.setCpf(faker.number().digits(11));
		request.setEmail(faker.internet().emailAddress());

		List<UUID> turmasIds = new ArrayList<>();
		turmasIds.add(turmaId);
		request.setTurmasIds(turmasIds);

		var result = mockMvc.perform(
				post("/api/alunos").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, AlunoResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getCpf(), request.getCpf());
		assertEquals(response.getEmail(), request.getEmail());

		alunoId = response.getId();
	}

	@Test
	@Order(5)
	void atualizarAlunoTest() throws Exception {

		var faker = new Faker(Locale.forLanguageTag("pt-BR"));

		var request = new AlunoRequestDto();
		request.setNome(faker.name().fullName());
		request.setCpf(faker.number().digits(11));
		request.setEmail(faker.internet().emailAddress());

		List<UUID> turmasIds = new ArrayList<>();
		turmasIds.add(turmaId);
		request.setTurmasIds(turmasIds);

		var result = mockMvc.perform(put("/api/alunos/" + alunoId).contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, AlunoResponseDto.class);
		
		assertEquals(response.getId(), alunoId);
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getCpf(), request.getCpf());
		assertEquals(response.getEmail(), request.getEmail());
	}

}
