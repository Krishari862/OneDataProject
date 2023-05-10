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

import com.pokemon.entity.Employees;
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

	
	 @GetMapping(path = "/employees", produces = "application/json")
	 public Employees getEmployees() { 
	  Employees response = new Employees();
	  ArrayList<Pokemon> list = new ArrayList<>();
	  pokemonRepository.findAll().forEach(e -> list.add(e));
	  response.setEmployeeList(list); 
	  return response;
	  }
	 @GetMapping(path = "/employees/type", produces = "application/json")
	 public Employees getEmployeestype(@RequestParam(required = false) PokemonType type) { 
	  Employees response = new Employees();
	  ArrayList<Pokemon> list = new ArrayList<>();
	  pokemonRepository.findByType(type).forEach(e -> list.add(e));
	  response.setEmployeeList(list); 
	  return response;
	  }
	 
	 @GetMapping(path = "/employees", produces = "application/json")
	    public ResponseEntity<Employees> getEmployees1() {
	        List<Pokemon> pokemons = new ArrayList<>();
	        pokemonRepository.findAll().forEach(pokemons::add);

	        Employees employees = new Employees();
	        employees.setEmployeeList(pokemons);

	        return ResponseEntity.ok().body(employees);
	    }

	    @GetMapping(path = "/employees/type", produces = "application/json")
	    public ResponseEntity<Employees> getEmployeestype1(@RequestParam(required = false) PokemonType type) {
	        List<Pokemon> pokemons = new ArrayList<>();
	        pokemonRepository.findByType(type).forEach(pokemons::add);

	        Employees employees = new Employees();
	        employees.setEmployeeList(pokemons);

	        return ResponseEntity.status(200).body(employees);
	    }

	 
/*
  @GetMapping(path = "/employees", produces = "application/json")
  public List<Pokemon> getPokemons(@RequestParam(required = false) PokemonType type) {
      if (type == null) {
          return pokemonRepository.findAll();
      } else {
          return pokemonRepository.findByType(type);
      }
  }*/
}