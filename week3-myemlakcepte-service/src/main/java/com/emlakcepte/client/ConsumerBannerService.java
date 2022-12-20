package com.emlakcepte.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "banners")
public class ConsumerBannerService {

	@Autowired
	RestTemplate restTemplate;

	/** week3-emlakcepte-banner-service'deki getAll'i kullanacak */
	@GetMapping
	public ResponseEntity<List<Banner>> getAll() {
		// Kullanılacak service'in url adresi girilir
		String resourceUrl = "http://localhost:8081/banners";

		// get istegi yapılır geri dönüş tipi girilir
		ResponseEntity<List> response = restTemplate.getForEntity(resourceUrl, List.class);

		return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
	}

	/** week3-emlakcepte-banner-service'deki create'i kullanacak */
	@PostMapping
	public ResponseEntity<String> create(@RequestBody Banner banner) {
		String resourceUrl = "http://localhost:8081/banners";

		HttpEntity<Banner> request = new HttpEntity<Banner>(banner);

		// post işlemi yapılır.url, post edilecek obje ve istenilen geri dönüş tipi girilir
		String createResponse = restTemplate.postForObject(resourceUrl, request, String.class);

		return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
	}

}
