package br.ce.wcaquino.tasks.apitest;

import static io.restassured.RestAssured.given;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ApiTest {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
				given()
					.log().all()
				.when()
					.get("/todo")
				.then()
					.statusCode(200)
				;
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		given()
			.body("{\"task\":\"Teste via Api\",\"dueDate\":\"2022-12-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(201)
		;
	
		
}
	
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
		given()
			.body("{\"task\":\"Teste via Api\",\"dueDate\":\"2020-12-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(400)
			.body("message",CoreMatchers.is("Due date must not be in past"))
		;
	}
}
