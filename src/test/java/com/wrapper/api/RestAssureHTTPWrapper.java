package com.wrapper.api;
import static com.spotify.base.SpecBuilder.getRequestSpec;
import static com.spotify.base.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class RestAssureHTTPWrapper {
	
	public static Response post(Object requestObj,String resource) {
		return given(getRequestSpec())
				.body(requestObj)
				.when()
				.post(resource)
				.then()
				.spec(getResponseSpec())
				.assertThat()
				.extract().response();
	}
	
	public static Response get(String resource) {
		return given(getRequestSpec())
				.when()
				.get(resource)
				.then()
				.spec(getResponseSpec())
				.assertThat()
				.statusCode(200)
				.and()
				.extract().response();
	}
	
	public static Response update(Object requestObj,String resource) {
		return given(getRequestSpec())
				.body(requestObj)
				.when()
				.put(resource)
				.then()
				.spec(getResponseSpec())
				.assertThat()
				.statusCode(200)
				.and()
				.extract().response();
	}

}
