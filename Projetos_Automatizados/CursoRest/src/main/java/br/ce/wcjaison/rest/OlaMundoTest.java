package br.ce.wcjaison.rest;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
		System.out.println(response.getBody().asString().equals("Ola Mundo!"));
		System.out.println(response.getStatusCode() == 200);
		
		 Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		 Assert.assertTrue(response.getStatusCode() == 200);
		 Assert.assertTrue("O status code deveria ser 200" ,response.getStatusCode() == 200);
		 Assert.assertEquals(200, response.statusCode());
		 
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		
		Response response = request(Method.GET, "https://restapi.wcaquino.me/ola");
		 
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		
		given() // Pré Condições
		.when().get("https://restapi.wcaquino.me/ola") // Ação
		.then() // Assertivas
		.statusCode(200); 
	}    
	
	

}
