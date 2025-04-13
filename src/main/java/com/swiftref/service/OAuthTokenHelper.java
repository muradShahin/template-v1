package com.swiftref.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.swiftref.controller.SwiftRefController;
import com.swiftref.dto.GenericResponse;
import com.swiftref.utils.HeadersCreation;
import com.swiftref.utils.RequestHelper;
import com.swiftref.utils.UrlBuilder;

@Service
public class OAuthTokenHelper {

	private static final Logger logger = LoggerFactory.getLogger(OAuthTokenHelper.class);

	@Value("${backend.host}")
	private String backendUrl;

	@Value("${backend.token.endpoint}")
	private String tokenEndpoint;

	@Value("${backend.auth.token}")
	private String authToken;

	@Value("${backend.username}")
	private String username;

	@Value("${backend.password}")
	private String password;

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private HeadersCreation headersCreation;

	@Autowired
	private UrlBuilder urlBuilder;

	@Autowired
	private RequestHelper requestHelper;

	public ResponseEntity<GenericResponse> getToken() {
		HashMap<String, String> headerList = new HashMap<String, String>();
		headerList.put("Authorization", "Basic " + authToken);
		headerList.put("content-type", MediaType.APPLICATION_FORM_URLENCODED.toString());
		HttpHeaders headers = headersCreation.createHeaders(headerList);

		String tokenUrl = urlBuilder.buildTokenUrl(backendUrl, tokenEndpoint);

		HashMap<String, String> requestFields = new HashMap<String, String>();

		requestFields.put("grant_type", "password");
		requestFields.put("username", username);
		requestFields.put("password", password);
		String formData = requestHelper.createFormUrlEncodedRequest(requestFields);

		logger.info("Requesting token from URL: {}", tokenUrl);
		logger.info("Request body", formData);

		try {
			return restTemplateService.httpClient(tokenUrl,HttpMethod.POST, headers, formData);
		} catch (Exception e) {
			logger.error("error while calling swiftref: ", e.getMessage());
			GenericResponse errorRes = new GenericResponse();
			errorRes.setMessage(e.getMessage());
			errorRes.setResponse(e.getMessage());
			errorRes.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

			return new ResponseEntity<GenericResponse>(errorRes, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	private String createFormData() {
		Map<String, String> formData = new HashMap<>();
		formData.put("grant_type", "password");
		formData.put("username", username);
		formData.put("password", password);

		StringBuilder encodedFormData = new StringBuilder();
		formData.forEach((key, value) -> {
			if (encodedFormData.length() > 0) {
				encodedFormData.append("&");
			}
			encodedFormData.append(key).append("=").append(value);
		});

		return encodedFormData.toString();
	}

}
