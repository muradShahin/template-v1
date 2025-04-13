package com.swiftref.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Service
public class HeadersCreation {

	private static final Logger logger = LoggerFactory.getLogger(HeadersCreation.class);

	public HttpHeaders createHeaders(HashMap<String, String> apiHeaders) {
		HttpHeaders headers = new HttpHeaders();

		for (Map.Entry<String, String> entry : apiHeaders.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			logger.info("Key: " + key + ", Value: " + value);
			headers.set(key, value);
		}

		return headers;
	}

}
