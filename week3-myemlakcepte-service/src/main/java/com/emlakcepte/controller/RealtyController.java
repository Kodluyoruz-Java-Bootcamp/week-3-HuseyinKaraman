package com.emlakcepte.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.model.Realty;
import com.emlakcepte.service.RealtyService;

@RestController
@RequestMapping(value = "/realtyes")
public class RealtyController {

	@Autowired
	private RealtyService realtyService;

	/** @Note : oluşturulan ilanları verir
	 *  @return
	 */
	@GetMapping
	public List<Realty> getAll() {
		return realtyService.getAll();
	}

	/** @Note : Realty oluşturmamızı saglar, Realty'i oluşturulurken userId girilmeli 
	 *  @Ornek : {
		    	"title": "satılık villa",
			    "userId":  101,
			    "province": "istanbul",
			    "district": "bakırköy",
			    "status": "PASSIVE",
			    "categoryType": "SALE",
			    "propertyType": "RESIDENTAL" 
			 }
		@VEYA: { "userId":101 }         şeklinde postmanda body kısmına girilebilir
	 * 
	 * */
	@PostMapping
	public ResponseEntity<Realty> create(@RequestBody Realty realty) {
		realtyService.createRealty(realty);
		return new ResponseEntity<>(realty, HttpStatus.CREATED);
	}

	/** @Ornek: http://localhost:8080/realtyes/istanbul,ankara,sivas veya 
	 *  @Veya:  http://localhost:8080/realtyes/istanbul
	 */
	@GetMapping(value = "/{province}")
	public List<Realty> findRealtyByProvince(@PathVariable String[] province) {
		return realtyService.findAllRealtyByProvince(province);
	}

	
	/**
	 * @Note: girilen il ve ilçeye göre arama yapılır.  
	 * @Ornek: http://localhost:8080/realtyes/istanbul/bakırköy,esenler
	 * @param province
	 * @param district
	 * @return
	 */
	@GetMapping(value = "/{province}/{district}")
	public List<Realty> findRealtyByProvinceAndDistinct(@PathVariable String province,
			@PathVariable String... district) {
		return realtyService.findRealtyByProvinceAndDistrict(province, district);
	}

	
	/** @Note:  girilen ilde bulunanilan sayısını verir.  
	 *  @Ornek: http://localhost:8080/realtyes/istanbul,ankara,sivas/size veya 
	 *  @Veya:  http://localhost:8080/realtyes/istanbul/size
	 */
	@GetMapping(value = "/{province}/size")
	public String findRealtySizeByProvince(@PathVariable String[] province) {
		int size = realtyService.findAllRealtyByProvince(province).size();
		return Arrays.toString(province) + " illerinde bulunan satılık ilan sayisi " + size;
	}

	
	/** @Note: girilen il veya illerde bulunan satılık konutları sayısını verir 
	 *  @Ornek: http://localhost:8080/realtyes/istanbul,ankara,sivas/salesize
	 *  @Veya: http://localhost:8080/realtyes/istanbul/salesize
	 */
	@GetMapping(value = "/{province}/salesize")
	public String findSaleRealtySizeByProvince(@PathVariable String[] province) {
		int size = realtyService.findSaleRealtyByProvince(province).size();
		return Arrays.toString(province) + " illerinde bulunan satılık konut ilan sayisi " + size;
	}
	
	/* @Note: girilen il ismine göre oluşturulmus vitrin verilir. */
	@GetMapping(value = "/showcase/{province}")
	public List<Realty> showcase(@PathVariable String province){
		return realtyService.provinceShowcase(province);
	}

}
