package com.emlakcepte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Banner;
import com.emlakcepte.service.BannerService;


@RequestMapping(value = "/banners") 
@RestController
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@PostMapping
	public Banner create(@RequestBody Banner banner) {
		bannerService.create(banner);
		return banner;
	}

	@GetMapping
	public ResponseEntity<List<Banner>> getAll() {
		return new ResponseEntity<>(bannerService.getAll(), HttpStatus.OK);
	}

}
