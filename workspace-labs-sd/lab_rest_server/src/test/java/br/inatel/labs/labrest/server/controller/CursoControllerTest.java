package br.inatel.labs.labrest.server.controller;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.PageAttributes.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.inatel.labs.labrest.server.model.Curso;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CursoControllerTest {

	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void deveListarCursos() {
	
	webTestClient.get()
		.uri("/curso")
		.exchange()
		.expectStatus().isOk() // status is Ok?
		.expectBody().returnResult(); // retornou algum resultado?	
	}
	
	
	@Test
	void dadoCursoIdValido_quandoGetCursPorId_entaoResponseComCursoValido(){
		Long cursoIdValido = 1L;
		
		Curso cursoRespondido = webTestClient.get()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody(); 
				
		assertNotNull(cursoRespondido);
		assertEquals(cursoRespondido.getId(), cursoIdValido);
		
		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoIdValido).isEqualTo(cursoRespondido.getId());	
		
	}
	
	@Test
	void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFOund() {
		Long cursoIdInvalido = 99L;
		
		webTestClient.get()
			.uri("/curso/" + cursoIdInvalido)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	// DESAFIO
	
	// POST
	@Test
	void dadoNovoCurso_quandoPostCurso_entaoResponseComStatusCreatedECursoValido() {
		Curso novoCurso = new Curso();
		novoCurso.setDescricao("Testando REST com Spring WebFlux");
		novoCurso.setCargaHoraria(120);
		
		Curso cursoRespondido = webTestClient.post()
			.uri("/curso")
			.bodyValue(novoCurso)
			.exchange()
			.expectStatus()
				.isCreated()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoRespondido.getId()).isNotNull();
	}
	
	// PUT
	@Test
	void dadoCurso_quandoPutCurso_entaoResponseComStatusAceptedECursoValido() {
		Long cursoIdValido = 1L;
		
		Curso novoCurso = new Curso();
		novoCurso.setId(cursoIdValido);
		novoCurso.setDescricao("Testando REST com Spring WebFlux editado");
		novoCurso.setCargaHoraria(120);
		
		 Curso cursoRespondido = webTestClient.put()
				.uri("/curso/" + cursoIdValido)
				 .bodyValue(novoCurso)
				.exchange()
				.expectStatus().isAccepted()
				.expectBody(Curso.class)
					.returnResult()
					.getResponseBody();
	} 
	
	// DELETE
	@Test
	void dadoCurso_quandoDeleteCurso_entaoResponseComStatusDeletedECursoValido1() {
		Long cursoIdValido = 1L;
	
		Curso cursoRespondido = webTestClient.delete()
			.uri("/curso/" + cursoIdValido)
			.exchange()
			.expectStatus()
				.isNoContent()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoRespondido).isNull();
	}
	
	@Test
	void dadoCurso_quandoDeleteCurso_entaoResponseComStatusDeletedECursoValido2() {
		Long cursoIdValido = 2L;
		
		Curso cursoRespondido = webTestClient.delete()
				.uri("/curso/" + cursoIdValido)
			
			.exchange()
			.expectStatus()
				.isNoContent()
			.expectBody(Curso.class)
				.returnResult()
				.getResponseBody();
		
		assertThat(cursoRespondido).isNull();
	}
}
