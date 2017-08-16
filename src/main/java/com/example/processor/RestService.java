package com.example.processor;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.processor.filetype.LeadTimeFile;
import com.example.processor.filetype.OrderDetailFile;
import com.example.processor.filetype.OrderHeaderFile;
import com.example.processor.filetype.PimFile;
import com.example.processor.filetype.PimFileDefinition;
import com.example.processor.filetype.SkuFile;
import com.example.util.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestService {
	private static final Logger logger = LoggerFactory.getLogger(RestService.class);

	RestTemplate rt;

	public RestService(RestTemplateBuilder restTemplateBuilder) {
		//@#@# debugging code
//		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
//		interceptors.add(new LoggingRequestInterceptor());
//		rt = restTemplateBuilder.interceptors(interceptors).build();
		//@#@# end debugging code
		
		rt = restTemplateBuilder
				.requestFactory(HttpComponentsClientHttpRequestFactory.class)
				.setConnectTimeout(120000)
				.setReadTimeout(120000)
				.build();
		
		rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public void putOrderHeader(OrderHeaderFile orderHeaderFile) {
		final String url = Constants.orderHeaderUrl;
		OrderHeaderFile result = rt.postForObject(url, orderHeaderFile, OrderHeaderFile.class);
	}

	public void putOrderDetail(OrderDetailFile orderDetailFile) {
		// we need to execute a get to determine if there's already an order detail so we know to post or put
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderNo", orderDetailFile.getOrderNo().toString());
		params.put("itemNo", orderDetailFile.getItemNo().toString());

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> entity = new HttpEntity<Object>(headers);

			ResponseEntity<String> out = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			try {
				out = rt.exchange(Constants.orderDetailSearchUrl, HttpMethod.GET, entity, String.class, params);
			} catch (HttpClientErrorException e) {
				if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					out = new ResponseEntity<String>(e.getStatusCode());
				} else {
					throw e;
				}
			}
			logger.debug(out.getBody());
			logger.debug(out.getStatusCode().toString());

			// http 200 means we have existing record to update
			// htto 404 means no data found so create new record
			if (out.getStatusCode().is2xxSuccessful()) {
				ObjectMapper om = new ObjectMapper();
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				OrderDetailFile json = om.readValue(out.getBody().getBytes(), OrderDetailFile.class);
				logger.debug(json.get_links().getSelf().getHref());
				rt.put(json.get_links().getSelf().getHref(), orderDetailFile);
			} else if (out.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				rt.postForLocation(Constants.orderDetailUrl, orderDetailFile);
			} else {
				logger.error("unknown error performing order detail search: BODY: " + out.getBody() + " STATUS: " + out.getStatusCodeValue());
				return;
			}
		} catch (Exception e) {
			logger.error("error calling order detail search service", e);
		}
	}

	public void putSku(SkuFile skuFile) {
		final String url = Constants.skuUrl;
		SkuFile result = rt.postForObject(url, skuFile, SkuFile.class);
	}

	public void putLeadTime(LeadTimeFile leadTimeFile) {
		// we need to execute a get to determine if there's already a lead time so we know to post or put
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderType", leadTimeFile.getOrderType());
		params.put("warehouse", leadTimeFile.getWarehouse());
		params.put("walmartDc", leadTimeFile.getWalmartDc());

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> entity = new HttpEntity<Object>(headers);

			ResponseEntity<String> out = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			try {
				out = rt.exchange(Constants.LeadTimeSearchUrl, HttpMethod.GET, entity, String.class, params);
			} catch (HttpClientErrorException e) {
				if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					out = new ResponseEntity<String>(e.getStatusCode());
				} else {
					throw e;
				}
			}
			logger.debug(out.getBody());
			logger.debug(out.getStatusCode().toString());
					
			// http 200 means we have existing record to update
			// htto 404 means no data found so create new record
			if (out.getStatusCode().is2xxSuccessful()) {
				ObjectMapper om = new ObjectMapper();
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				LeadTimeFile json = om.readValue(out.getBody().getBytes(), LeadTimeFile.class);
				logger.debug(json.get_links().getSelf().getHref());
				rt.put(json.get_links().getSelf().getHref(), leadTimeFile);
			} else if (out.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				rt.postForLocation(Constants.LeadTimeUrl, leadTimeFile);
			} else {
				logger.error("unknown error performing lead time search: BODY: " + out.getBody() + " STATUS: " + out.getStatusCodeValue());
				return;
			}
		} catch (Exception e) {
			logger.error("error calling lead time search service", e);
		}
	}

	public void putPim(PimFile pimFile) {
		// we need to execute a get to determine if there's already a PIM for the UPC/start ship date so we know to post or put
		Map<String, String> params = new HashMap<String, String>();
		params.put("upc", pimFile.getUpc());
		params.put("shipDate", pimFile.getShipDate().toString());
		params.put("color", pimFile.getColor());
		params.put("size", pimFile.getSize());
		

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Object> entity = new HttpEntity<Object>(headers);

			ResponseEntity<String> out = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			try {
				out = rt.exchange(Constants.pimSearchUrl, HttpMethod.GET, entity, String.class, params);
			} catch (HttpClientErrorException e) {
				if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
					out = new ResponseEntity<String>(e.getStatusCode());
				} else {
					throw e;
				}
			} catch (Exception e) {
				logger.error("caught http error", e);
			}
			logger.debug(out.getBody());
			logger.debug(out.getStatusCode().toString());
			
			// http 200 means we have existing record to update
			// htto 404 means no data found so create new record
			if (out.getStatusCode().is2xxSuccessful()) {
				ObjectMapper om = new ObjectMapper();
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				PimFile json = om.readValue(out.getBody().getBytes(), PimFile.class);
				logger.debug(json.get_links().getSelf().getHref());
				rt.put(json.get_links().getSelf().getHref(), pimFile);
			} else if (out.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				rt.postForLocation(Constants.pimUrl, pimFile);
			} else {
				logger.error("unknown error performing pim search: BODY: " + out.getBody() + " STATUS: " + out.getStatusCodeValue());
				return;
			}
		} catch (Exception e) {
			logger.error("error calling pim search service", e);
		}
	}
}
