package com.swiftref.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class RequestHelper {

	private static final Logger logger = LoggerFactory.getLogger(RequestHelper.class);

	@Bean
	public String createFormUrlEncodedRequest(HashMap<String, String> requestFields) {
	
		logger.info("Before building the request"+requestFields.getOrDefault("username", null));
		StringBuilder encodedFormData = new StringBuilder();
		requestFields.forEach((key, value) -> {
			if (encodedFormData.length() > 0) {
				encodedFormData.append("&");
			}
			encodedFormData.append(key).append("=").append(value);
		});

		return encodedFormData.toString();
	}

}
