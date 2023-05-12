package com.pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import com.pokemon.entity.Pokemon;
import com.pokemon.entity.PokemonType;
import com.pokemon.entity.Pokemons;
import com.pokemon.controller.PokemonController;
import com.pokemon.repo.PokemonRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
public class PokemonControllerUnitTests 
{ 
	@InjectMocks
	PokemonController pokemonController;
	
	@Mock
	PokemonRepository pokemonRepository;
	

	
	@Test
	public void testFindAll() 
	{
		// given
		Pokemon pokemon1 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon pokemon2 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(pokemon1, pokemon2));

		when(pokemonRepository.findAll()).thenReturn(list);

		// when
		Pokemons result =pokemonController.getPokemons();
		// then
		assertThat(result.getPokemonList().size()).isEqualTo(2);
		
		assertThat(result.getPokemonList().get(0).getName())
						.isEqualTo(pokemon1.getName()); 
		
		assertThat(result.getPokemonList().get(1).getName())
					.isEqualTo(pokemon2.getName());
	
	}
	@Test
	public void testFindByType() 
	{
		// given
		Pokemon pokemon1 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon pokemon2 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(pokemon1, pokemon2));

		when(pokemonRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		
		Pokemons result =pokemonController.getPokemontype(PokemonType.Ghost);
		//then
	//	assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(result.getPokemonList().size()).isEqualTo(2);
		
		assertThat(result.getPokemonList().get(0).getName())
						.isEqualTo(pokemon1.getName()); 
		
	
	}
	@Test
	public void testFindByType1() 
	{
		// given
		Pokemon pokemon1 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon pokemon2 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(pokemon1, pokemon2));

		when(pokemonRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		 MockHttpServletRequest request = new MockHttpServletRequest();
		    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		    ResponseEntity<Pokemons> response = pokemonController.getPokemontype1(PokemonType.Ghost);

		    // then
		    assertThat(response.getStatusCode().value()).isEqualTo(200);
		    assertThat(response.getBody().getPokemonList().size()).isEqualTo(2);
		    assertThat(response.getBody().getPokemonList().get(0).getName()).isEqualTo(pokemon1.getName());
		}
	@Test
	public void testFindByTypeerror1() 
	{
		// given
		Pokemon pokemon1 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon pokemon2 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(pokemon1, pokemon2));

		when(pokemonRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		 MockHttpServletRequest request = new MockHttpServletRequest();
		    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		    ResponseEntity<Pokemons> response = pokemonController.getPokemontype1(PokemonType.Fire);

		    // then
		    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		  //  assertThat(response.getBody().getPokemonList().size()).isEqualTo(2);
		  //  assertThat(response.getBody().getPokemonList().get(0).getName()).isEqualTo(pokemon1.getName());
		}
}
