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

		webTestClient.get().uri("/curso").exchange().expectStatus().isOk() // status is Ok?
				.expectBody().returnResult(); // retornou algum resultado?
	}

	@Test
	void dadoCursoIdValido_quandoGetCursPorId_entaoResponseComCursoValido() {
		Long cursoIdValido = 1L;

		Curso cursoRespondido = webTestClient.get().uri("/curso/" + cursoIdValido).exchange().expectStatus().isOk()
				.expectBody(Curso.class).returnResult().getResponseBody();

		assertNotNull(cursoRespondido);
		assertEquals(cursoRespondido.getId(), cursoIdValido);

		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoIdValido).isEqualTo(cursoRespondido.getId());

	}

	@Test
	void dadoCursoIdInvalido_quandoGetCursoPeloId_entaoRespondeComStatusNotFOund() {
		Long cursoIdInvalido = 99L;

		webTestClient.get().uri("/curso/" + cursoIdInvalido).exchange().expectStatus().isNotFound();
	}

	// DESAFIO

	// POST (Precisa do id no @PutMapping)
	@Test
	void dadoNovoCurso_quandoPostCurso_entaoResponseComStatusCreatedECursoValido() {
		Curso novoCurso = new Curso();
		novoCurso.setDescricao("Testando REST com Spring WebFlux");
		novoCurso.setCargaHoraria(120);

		Curso cursoRespondido = webTestClient.post().uri("/curso").bodyValue(novoCurso).exchange().expectStatus()
				.isCreated().expectBody(Curso.class).returnResult().getResponseBody();

		assertThat(cursoRespondido).isNotNull();
		assertThat(cursoRespondido.getId()).isNotNull();
	}

	// PUT (Precisa do id no @PutMapping)
	/* @Test
	void dadoCurso_quandoPutCurso_entaoResponseComStatusAceptedECursoValido() {
		Long cursoIdValido = 1L;

		Curso novoCurso = new Curso();
		novoCurso.setId(cursoIdValido);
		novoCurso.setDescricao("Testando REST com Spring WebFlux editado");
		novoCurso.setCargaHoraria(120);

		webTestClient.put().uri("/curso/" + cursoIdValido).bodyValue(novoCurso).exchange().expectStatus().isAccepted();
	} */

	// PUT DO PROFESSOR
	@Test
	void dadoCursoExistente_quandoPutCurso_entaoRespondeComStatusAcept() {
		Curso cursoExistente = new Curso();
		cursoExistente.setId(1L);
		cursoExistente.setDescricao("Testando REST com Spring WebFlux editado");
		cursoExistente.setCargaHoraria(111);

		webTestClient.put().uri("/curso").bodyValue(cursoExistente).exchange().expectStatus().isAccepted().expectBody()
				.isEmpty();

	}

	// DELETE
	@Test
	void dadoCursoIdValido_quandoDeleteCursoPeloId_entaoRespondeComStatusNoContentECorpoVazio() {
		Long cursoIdValido = 2L;

		webTestClient.delete().uri("/curso/" + cursoIdValido)

				.exchange().expectStatus().isNoContent().expectBody().isEmpty();
	}
	

}
