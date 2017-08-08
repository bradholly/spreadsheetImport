package com.example.processor;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.processor.filetype.OrderHeaderFile;

@Component
public class RestService {

    private final RestTemplate restTemplate;
    
    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

	public void putOrderHeader(OrderHeaderFile orderHeaderFile) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(RESTConfiguration.class);
		final String url = "https://jpademo.local.pcfdev.io/orderHeaders";

		RestTemplate rt = new RestTemplate();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rt.getMessageConverters().add(new StringHttpMessageConverter());
		OrderHeaderFile result = rt.postForObject(url, orderHeaderFile, OrderHeaderFile.class);
		
		
		
		
		
		
	}
}
