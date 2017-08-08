package com.example.processor;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.processor.filetype.OrderDetailFile;
import com.example.processor.filetype.OrderHeaderFile;
import com.example.util.Constants;

@Component
public class RestService {

    private final RestTemplate rt;
    
    public RestService(RestTemplateBuilder restTemplateBuilder) {
        rt = restTemplateBuilder.build();
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		rt.getMessageConverters().add(new StringHttpMessageConverter());
    }

	public void putOrderHeader(OrderHeaderFile orderHeaderFile) {
//		RestTemplate rt = new RestTemplate();
//		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//		rt.getMessageConverters().add(new StringHttpMessageConverter());

		final String url = Constants.orderHeaderUrl;
		OrderHeaderFile result = rt.postForObject(url, orderHeaderFile, OrderHeaderFile.class);
	}

	public void putOrderDetail(OrderDetailFile orderDetailFile) {
		// we need to execute a get to determine if there's already an order detail so we know to post or put
		Integer orderNo = orderDetailFile.getOrderNo();
		Integer itemNo = orderDetailFile.getItemNo();
		String getResponse = rt.getForObject(Constants.orderDetailSearchUrl, String.class, orderNo, itemNo);
		
		
		final String url = Constants.orderDetailUrl;
		OrderDetailFile result = rt.postForObject(url, orderDetailFile, OrderDetailFile.class);
	}
}
