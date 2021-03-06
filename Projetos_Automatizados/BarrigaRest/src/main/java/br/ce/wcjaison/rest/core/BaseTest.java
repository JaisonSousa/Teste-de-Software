package br.ce.wcjaison.rest.core;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

public class BaseTest implements Contantes {
	
	@BeforeClass
	public static void setup() {
		
		RestAssured.baseURI = APP_BASE_URL;
		//RestAssured.port = APP_PORT;
		RestAssured.basePath = APP_BASE_PATH;
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(APP_CONTENT_TYPE);
		RestAssured.requestSpecification = reqBuilder.build();
		
		ResponseSpecBuilder resBulder = new ResponseSpecBuilder();
		resBulder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
		RestAssured.responseSpecification = resBulder.build();
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
	}
	

}
