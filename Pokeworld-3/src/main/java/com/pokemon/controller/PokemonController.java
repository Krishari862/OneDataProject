package com.pokemon.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pokemon.entity.Pokemons;
//import com.howtodoinjava.rest.dao.EmployeeRepository;
//import com.howtodoinjava.rest.model.Employee;
//import com.howtodoinjava.rest.model.Employees;
import com.pokemon.entity.Pokemon;
import com.pokemon.entity.PokemonType;
import com.pokemon.repo.PokemonRepository;

@RestController
public class PokemonController {

	@Autowired
	private PokemonRepository pokemonRepository;

	@GetMapping(path = "/Pokemons", produces = "application/json")
	public Pokemons getPokemons() {
		Pokemons response = new Pokemons();
		ArrayList<Pokemon> list = new ArrayList<>();
		pokemonRepository.findAll().forEach(e -> list.add(e));
		response.setPokemonList(list);
		return response;
	}

	@GetMapping(path = "/Pokemons/type", produces = "application/json")
	public Pokemons getPokemontype(@RequestParam(required = false) PokemonType type) {
		Pokemons response = new Pokemons();
		ArrayList<Pokemon> list = new ArrayList<>();
		pokemonRepository.findByType(type).forEach(e -> list.add(e));
		response.setPokemonList(list);
		return response;
	}

	@GetMapping(path = "/Pokemons1", produces = "application/json")
	public ResponseEntity<Pokemons> getPokemons1() {
		List<Pokemon> pokemon = new ArrayList<>();
		pokemonRepository.findAll().forEach(pokemon::add);

		Pokemons pokemons = new Pokemons();
		pokemons.setPokemonList(pokemon);

		return ResponseEntity.ok().body(pokemons);
	}

	@GetMapping(path = "/Pokemons/type1")
	public ResponseEntity<Pokemons> getPokemontype1(@RequestParam(required = false) PokemonType type) {
		List<Pokemon> pokemons = new ArrayList<>();
		pokemonRepository.findByType(type).forEach(pokemons::add);

		Pokemons pokemons2 = new Pokemons();
		pokemons2.setPokemonList(pokemons);

		return ResponseEntity.status(200).body(pokemons2);
	}

	/*
	 * @GetMapping(path = "/employees", produces = "application/json") public
	 * List<Pokemon> getPokemons(@RequestParam(required = false) PokemonType type) {
	 * if (type == null) { return pokemonRepository.findAll(); } else { return
	 * pokemonRepository.findByType(type); } }
	 */
}