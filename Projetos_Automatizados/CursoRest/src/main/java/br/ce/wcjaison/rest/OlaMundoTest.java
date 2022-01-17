package br.ce.wcjaison.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

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
	
	@Test
	public void devoConhecerMatcherHamcrest() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.isA(Double.class));
		Assert.assertThat(128d, Matchers.greaterThan(120d));
		Assert.assertThat(128d, Matchers.lessThan(130d));
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares, contains(1,3,5,7,9));
		assertThat(impares, containsInAnyOrder(1,3,5,9,7));
		assertThat(impares, hasItem(1));
		assertThat(impares, hasItems(1,5));
		
		assertThat("Maria", is(not("João")));
		assertThat("Maria", not("João"));
		assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
		assertThat("Joaquina", anyOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
		
		

	}
	
	@Test
	public void devoValidarBody() {
		
		given() // Pré Condições
		.when().get("https://restapi.wcaquino.me/ola") // Ação
		.then() // Assertivas
		.statusCode(200)
		.body(is("Ola Mundo!"))
		.body(containsString("Mundo"))
		.body(is(not(nullValue())));
		
		
	}
	
	private static String requestBody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"bar\",\n" +
            "  \"userId\": \"1\" \n}";
	
	@Test
	public void testMetodoPost() {
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		RequestSpecification req = RestAssured.given();
		req.header("Content-Type", "application/json");
		req.body(requestBody);
		Response res = req.post("/posts");
		int code = res.getStatusCode();
		
		System.out.println(code);
		System.out.println("Response body: " + res.body().asString());
		
//		Response response = RestAssured.request(Method.GET, "/ola");
//		System.out.println(response.getBody().asString().equals("Ola Mundo!"));
//		System.out.println(response.getStatusCode() == 200);
//		
//		 Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
//		 Assert.assertTrue(response.getStatusCode() == 200);
//		 Assert.assertTrue("O status code deveria ser 200" ,response.getStatusCode() == 200);
//		 Assert.assertEquals(200, response.statusCode());
//		 
//		ValidatableResponse validacao = response.then();
//		validacao.statusCode(200);
		
	}
	

}
