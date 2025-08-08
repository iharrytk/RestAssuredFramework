package com.qa.gorest.client;

import java.util.Map;

import com.qa.gorest.frameworkexception.APIFrameworkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private static final String BASE_URI = "https://gorest.co.in";
	private static final String BEARER_TOKEN = "a2a16ab3f6d4ebe774d5f32f04b1302568d98508acffabeb506634a48037fa18";

	private static RequestSpecBuilder specBuilder;

	static {

		specBuilder = new RequestSpecBuilder();
	}

	public void addAuthorizationHeader() {

		specBuilder.addHeader("Authorization", "Bearer " + BEARER_TOKEN);
	}

	private void setRequestContentType(String contentType) {

		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("plz pass the right content type...");
			throw new APIFrameworkException("INVALIDCONTENTTYPE");

		}

	}

	private RequestSpecification createRequestSpec() {

		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();

		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap) {

		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if (headersMap != null) {

			specBuilder.addHeaders(headersMap);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, String> queryParams) {

		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if (headersMap != null) {

			specBuilder.addHeaders(headersMap);
		}
		if (queryParams != null) {
			specBuilder.addQueryParams(queryParams);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {

		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);

		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType,
			Map<String, String> headersMap) {

		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContentType(contentType);
		if (headersMap != null) {
			specBuilder.addHeaders(headersMap);
		}

		if (requestBody != null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();

	}

	public Response get(String serviceURL, boolean log) {

		if (log) {
			return RestAssured.given(createRequestSpec()).log().all().when().get(serviceURL);

		}
		return RestAssured.given(createRequestSpec()).when().get(serviceURL);

	}

	public Response get(String serviceURL, boolean log, Map<String, String> headersMap) {

		if (log) {
			return RestAssured.given(createRequestSpec(headersMap)).log().all().when().get(serviceURL);

		}
		return RestAssured.given(createRequestSpec(headersMap)).when().get(serviceURL);

	}

	public Response get(String serviceURL, boolean log, Map<String, String> headersMap,
			Map<String, String> queryParams) {

		if (log) {
			return RestAssured.given(createRequestSpec(headersMap, queryParams)).log().all().when().get(serviceURL);

		}
		return RestAssured.given(createRequestSpec(headersMap, queryParams)).when().get(serviceURL);

	}

	public Response post(String serviceURL, boolean log, Map<String, String> headersMap, String contentType,
			Object requestBody) {

		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when()
					.post(serviceURL);

		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().post(serviceURL);

	}

	public Response post(String serviceURL, boolean log, String contentType, Object requestBody) {

		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().post(serviceURL);

		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().post(serviceURL);

	}

	public Response put(String serviceURL, boolean log, Map<String, String> headersMap, String contentType,
			Object requestBody) {

		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when()
					.put(serviceURL);

		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().put(serviceURL);

	}

	public Response put(String serviceURL, boolean log, String contentType, Object requestBody) {

		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().put(serviceURL);

		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().put(serviceURL);

	}

	public Response patch(String serviceURL, boolean log, Map<String, String> headersMap, String contentType,
			Object requestBody) {

		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all().when()
					.patch(serviceURL);

		}
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).when().patch(serviceURL);

	}

	public Response patch(String serviceURL, boolean log, String contentType, Object requestBody) {

		if (log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all().when().patch(serviceURL);

		}
		return RestAssured.given(createRequestSpec(requestBody, contentType)).when().patch(serviceURL);

	}

	public Response delete(String serviceUrl, boolean log) {

		if (log) {

		return	RestAssured.given(createRequestSpec()).log().all().when().delete(serviceUrl);
		}

		return RestAssured.given(createRequestSpec()).log().all().when().delete(serviceUrl);

	}

}
