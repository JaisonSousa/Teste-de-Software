package br.ce.wcjaison.rest;

import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileTest {
	
	@Test
	public void deveObrigarEnvioArquivo() {
		
		given()
		.log().all()
		.when()
		.post("https://restapi.wcaquino.me/upload")
		.then()
		.log().all()
         .statusCode(404)
         .body("error", is("Arquivo não enviado"))
		;

	}
	
	@Test
	public void deveFazerUploudArquivo() {
		
		given()
		.log().all()
		.multiPart("arquivo", new File("src/main/resources/users.pdf"))
		.when()
		.post("https://restapi.wcaquino.me/upload")
		.then()
		.log().all()
         .statusCode(200)
         .body("name", is("users.pdf"))
		;

	}
	
	@Test
	public void naoDeveFazerUploudArquivoGrande() {
		
		given()
		.log().all()
		.multiPart("arquivo", new File("src/main/resources/nome do arquivo"))
		.when()
		.post("https://restapi.wcaquino.me/upload")
		.then()
		.log().all()
		.time(lessThan(5000L))
         .statusCode(413)  
		;

	}
	
	@Test
	public void deveBaixarArquivo() throws IOException {
		
		byte[] Image = given()
		.log().all()
		//.multiPart("arquivo", new File("src/main/resources/nome do arquivo"))
		.when()
		.get("https://restapi.wcaquino.me/download")
		.then()
		//.log().all()
         .statusCode(200)
         .extract().asByteArray()
		;
		
		File imagem = new File("src/main/resources/file.jpg");
		OutputStream out = new FileOutputStream(imagem);
		
		out.write(Image);
		out.close();
		
		System.out.println(imagem.length());
		Assert.assertThat(imagem.length(), lessThan(100000L));

	}

}
