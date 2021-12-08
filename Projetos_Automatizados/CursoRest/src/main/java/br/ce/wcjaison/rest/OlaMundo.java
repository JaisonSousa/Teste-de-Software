package br.ce.wcjaison.rest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class OlaMundo {
	
	public static void main(String[] args) {
		//Atalho Ctrl + 1 cria variavel
		Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me/ola");
		System.out.println(response.getBody().asString());
		
	}

}
