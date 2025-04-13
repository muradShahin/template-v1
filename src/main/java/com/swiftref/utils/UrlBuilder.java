package com.swiftref.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UrlBuilder {

	public String buildTokenUrl(String backendUrl, String tokenEndpoint) {

		return UriComponentsBuilder.fromUriString("https://" + backendUrl + tokenEndpoint).toUriString();
	}

}
