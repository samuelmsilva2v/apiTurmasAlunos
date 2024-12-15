package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
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
	
	private static UUID id;
	
	@Test
	@Order(1)
	void cadastrarTurmaTest() throws Exception {
		
		var faker = new Faker(Locale.forLanguageTag("pt-BR"));
		
		var request = new TurmaRequestDto();
		request.setNumero(faker.number().digits(4));
		request.setAnoLetivo(faker.number().randomDigit());
		
		var result = mockMvc.perform(post("/api/turmas")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		var response = objectMapper.readValue(content, TurmaResponseDto.class);
		
		assertNotNull(response.getId());
		assertEquals(response.getNumero(), request.getNumero());
		assertEquals(response.getAnoLetivo(), request.getAnoLetivo());
		
		id = response.getId();
	}
	
	@Test
	@Order(2)
	void consultarTurmaPorIdTest() throws Exception {
		
		var result = mockMvc.perform(get("/api/turmas/" + id)
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		var response = objectMapper.readValue(content, TurmaResponseDto.class);
		
		assertEquals(response.getId(), id);
		assertNotNull(response.getNumero());
		assertNotNull(response.getAnoLetivo());
	}

}
