package tests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;

public class SoapXmlRequest {
	@Test
	public void validateSoapXml() throws IOException {
		File file = new File("./soapRequest/Add.xml");
		if (file.exists())
			System.out.println(">>>>>>>>>>>file exist");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
	baseURI = "http://www.dneonline.com";
	given().
	     contentType("text/xml").accept(ContentType.XML)
	         .body(requestBody)
	         .when()
	         .post("/calculator.asmx")
	         .then()
	         .statusCode(200).log().all().
	         and().
	         body("//*:AddResult.text()", equalTo("7"));
	         
	}

}
