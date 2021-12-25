package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestSamples {
	@Test
	public void test_1() {
		
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.statusLine());
		System.out.println(response.getHeader("content-type"));
		int statusCode =response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
    @Test
	public void test_2() {
    	baseURI="https://reqres.in/api";
    	given().
    	 get("/users?page=2").
    	then().
    	 statusCode(200).
    	  body("data[1].id", equalTo(8))
    	  .log().all();
    	
		
	}
    @Test
    public void test_3() {
    	baseURI="https://reqres.in/api";
    	given().get("/users/2").then().statusCode(200).body("data.first_name", equalTo("Janet"));
    	
    }
    @Test
    public void test_4() {
    	baseURI="https://reqres.in/api";
    	given().get("users/23").then().statusCode(404);
    }
}
