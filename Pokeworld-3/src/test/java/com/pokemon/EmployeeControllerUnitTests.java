package com.pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import com.pokemon.entity.Pokemon;
import com.pokemon.entity.PokemonType;
import com.pokemon.entity.Employees;
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
public class EmployeeControllerUnitTests 
{ 
	@InjectMocks
	PokemonController employeeController;
	
	@Mock
	PokemonRepository employeeRepository;
	

	
	@Test
	public void testFindAll() 
	{
		// given
		Pokemon employee2 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon employee1 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(employee1, employee2));

		when(employeeRepository.findAll()).thenReturn(list);

		// when
		Employees result =employeeController.getEmployees();
		// then
		assertThat(result.getEmployeeList().size()).isEqualTo(2);
		
		assertThat(result.getEmployeeList().get(0).getName())
						.isEqualTo(employee1.getName()); 
		
		assertThat(result.getEmployeeList().get(1).getName())
					.isEqualTo(employee2.getName());
	
	}
	@Test
	public void testFindByType() 
	{
		// given
		Pokemon employee2 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon employee1 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(employee1, employee2));

		when(employeeRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		
		Employees result =employeeController.getEmployeestype(PokemonType.Ghost);
		//then
	//	assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(result.getEmployeeList().size()).isEqualTo(2);
		
		assertThat(result.getEmployeeList().get(0).getName())
						.isEqualTo(employee1.getName()); 
		
	
	}
	@Test
	public void testFindByType1() 
	{
		// given
		Pokemon employee2 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon employee1 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(employee1, employee2));

		when(employeeRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		 MockHttpServletRequest request = new MockHttpServletRequest();
		    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		    ResponseEntity<Employees> response = employeeController.getEmployeestype1(PokemonType.Ghost);

		    // then
		    assertThat(response.getStatusCode().value()).isEqualTo(200);
		    assertThat(response.getBody().getEmployeeList().size()).isEqualTo(2);
		    assertThat(response.getBody().getEmployeeList().get(0).getName()).isEqualTo(employee1.getName());
		}
	@Test
	public void testFindByTypeerror1() 
	{
		// given
		Pokemon employee2 = new Pokemon(1, "Gengar", PokemonType.Ghost);
		Pokemon employee1 = new Pokemon(2, "Pickachu",PokemonType.Electric);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(employee1, employee2));

		when(employeeRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		 MockHttpServletRequest request = new MockHttpServletRequest();
		    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		    ResponseEntity<Employees> response = employeeController.getEmployeestype1(PokemonType.Fire);

		    // then
		    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		  //  assertThat(response.getBody().getEmployeeList().size()).isEqualTo(2);
		  //  assertThat(response.getBody().getEmployeeList().get(0).getName()).isEqualTo(employee1.getName());
		}
}
