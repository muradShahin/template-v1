package com.swiftref.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.swiftref.dto.GenericResponse;
import com.swiftref.service.OAuthTokenHelper;
import com.swiftref.service.RestTemplateService;
import com.swiftref.utils.HeadersCreation;
import com.swiftref.utils.UrlBuilder;

import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class SwiftRefController {

	private static final Logger logger = LoggerFactory.getLogger(SwiftRefController.class);

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private HeadersCreation headersCreation;

	@Autowired
	private OAuthTokenHelper oAuthTokenHelper;

	@Autowired
	private UrlBuilder urlBuilder;

	@Value("${backend.host}")
	private String backendUrl;

	@Value("${backend.bic.validity.endpoint}")
	private String validityEndpoint;

	@Value("${backend.bic.getbic.endpoint}")
	private String getBicEndpoint;
	
	@Value("${bacend.bic.details.endpoint}")
	private String bicCodeDetails;

	@GetMapping("/v1/ibans/{iban}/validity")
	public ResponseEntity<GenericResponse> getIbanValidity(@PathVariable String iban) throws JsonProcessingException {

		// create the headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + getToken());
		HttpHeaders httpHeaders = headersCreation.createHeaders(headers);

		// build the url
		String url = urlBuilder.buildTokenUrl(backendUrl, validityEndpoint.replace("{iban}", iban));
		ResponseEntity<GenericResponse> response = restTemplateService.httpClient(url, HttpMethod.GET, httpHeaders, "");

		return response;
	}

	@GetMapping("/v1/ibans/{iban}/bic")
	public ResponseEntity<GenericResponse> getBicCode(@PathVariable String iban) throws JsonProcessingException {

		// create the headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + getToken());
		HttpHeaders httpHeaders = headersCreation.createHeaders(headers);

		// build the url
		String url = urlBuilder.buildTokenUrl(backendUrl, getBicEndpoint.replace("{iban}", iban));
		ResponseEntity<GenericResponse> response = restTemplateService.httpClient(url, HttpMethod.GET, httpHeaders, "");

		return response;

	}
	
	@GetMapping("/v1/bics/{bic}")
	public ResponseEntity<GenericResponse> getBicDetails(@PathVariable String bic) throws JsonProcessingException {

		// create the headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + getToken());
		HttpHeaders httpHeaders = headersCreation.createHeaders(headers);

		// build the url
		String url = urlBuilder.buildTokenUrl(backendUrl, bicCodeDetails.replace("{bic}", bic));
		ResponseEntity<GenericResponse> response = restTemplateService.httpClient(url, HttpMethod.GET, httpHeaders, "");

		return response;

	}

	public String getToken() throws JsonProcessingException {

		ResponseEntity<GenericResponse> response = oAuthTokenHelper.getToken();
		logger.info("After Request " + response.getBody().getResponse());
		ObjectMapper objectMapper = new ObjectMapper();
		Gson gson = new GsonBuilder().create();
		String jsonString = objectMapper.writeValueAsString(response.getBody().getResponse());
		JSONObject jsonObj = new JSONObject(jsonString);
		String accessToken = (String) jsonObj.get("access_token");
		logger.info("accessToken= " + accessToken);

		return accessToken;

	}

}
