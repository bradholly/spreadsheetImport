package com.example.processor;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestService {

    private final RestTemplate restTemplate;
    
    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

	public void putOrderHeader(String orderHeaderJson) {
		// TODO Auto-generated method stub
		
	}
    
    
}
