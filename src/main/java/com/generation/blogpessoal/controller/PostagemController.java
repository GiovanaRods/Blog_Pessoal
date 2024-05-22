package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*") 


public class PostagemController {
	@Autowired 
	private PostagemRepository postagemrepository;
	
	@GetMapping
	public ResponseEntity <List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemrepository.findAll());
	}

	@GetMapping ("/{id}")
	public ResponseEntity <Postagem> getById(@PathVariable Long id){
		return postagemrepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping ("/titulo/{titulo}")
	public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemrepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	//INSERT INTO tb_postagens (titulo, texto, data) VALUES ("Título", "Texto", CURRENT_TIMESTAMP(data e hora));
	@PostMapping 
	public ResponseEntity <Postagem> post(@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemrepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		
		return postagemrepository.findById(postagem.getId())
				.map(resposta-> ResponseEntity.status(HttpStatus.OK)
				.body(postagemrepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemrepository.findById(id);
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		postagemrepository.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
}
