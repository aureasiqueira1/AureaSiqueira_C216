package br.inatel.labs.labrest.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import br.inatel.labs.labrest.server.model.Curso;

// ctrl + shift + 0 => tira os imports

/**
 * 
 * Serviço com métod de negócio para curso
 * @author aurea
 * @since 03 set 2022
 */

@Service
public class CursoService {

	private List<Curso> listaDeCursos = new ArrayList<>();
	
	@PostConstruct
	private void setup() {
		Curso c1 = new Curso(1L, "REST com Spring Boot", 20);
		Curso c2 = new Curso(2L, "Programação Java 11", 80);
		Curso c3 = new Curso(3L, "Dominando a IDE Eclipse", 120);
		
		listaDeCursos.add( c1 );
		listaDeCursos.add( c2 );
		listaDeCursos.add( c3 );
	}
	
	public List<Curso> pesquisarCurso(){
		return listaDeCursos;
	}
	
	public Optional<Curso> buscarCursoPeloId(Long cursoId) {
		Optional<Curso> opCurso = listaDeCursos.stream()
		.filter(c -> c.getId().equals(cursoId))
		.findFirst();
		
		return opCurso;
	}
	
	// CRIAR CURSO
	public Curso criarCurso(Curso curso) {
		Long id = new Random().nextLong(); // long randômico
		curso.setId(id);
		listaDeCursos.add(curso);
		return curso;
	}
	
	// EDITAR CURSO
	public void atualizarCurso(Curso curso){
		listaDeCursos.remove(curso);
		listaDeCursos.add(curso);
	}
	
	// DELETAR CURSO
	public void removerCursoPeloId(Long cursoId){
		Optional<Curso> opCurso = buscarCursoPeloId(cursoId);
		if(opCurso.isPresent()){
			Curso cursoARemover = opCurso.get();
			listaDeCursos.remove(cursoARemover);
		}
	}
	
	// EDITAR CURSO
	public void removerCurso(Curso curso){
		listaDeCursos.remove(curso);
	}
	
	
	// PESQUISAR CURSO
	/**
	 * Pesquisa cursos pelo fragmento da descrição ignorandoespaços em branco e caixividade (maiúscula e minúscula)
	 * @param fragDescricao
	 * @return
	 */
	
	// pesquisa -> localhost:8080/curso/pesquisa?descricao=spring
	// o nome spring verifica se existe essa palavra no elemento e retorna o elemento que tiver
	
	public List<Curso> pesquisarCursoPeloFragDescricao(String fragDescricao){
		if(Objects.isNull(fragDescricao) || fragDescricao.isBlank()){
		return List.of();
		}
		
		List<Curso> listaDeCursosEncontrados = this.listaDeCursos.stream()
				.filter(c -> c.getDescricao().trim().toLowerCase().contains(fragDescricao.trim().toLowerCase()))
				.collect(Collectors.toList());
		return listaDeCursosEncontrados;
	}
	

	
}
