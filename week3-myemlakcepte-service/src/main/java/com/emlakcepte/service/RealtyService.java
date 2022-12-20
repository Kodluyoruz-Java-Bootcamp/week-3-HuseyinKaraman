package com.emlakcepte.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.client.Banner;
import com.emlakcepte.client.ConsumerBannerService;
import com.emlakcepte.model.Realty;
import com.emlakcepte.model.User;
import com.emlakcepte.model.enums.PropertyType;
import com.emlakcepte.model.enums.UserType;
import com.emlakcepte.repository.RealtyRepository;

@Service
public class RealtyService {

	@Autowired
	private RealtyRepository realtyRepository;

	@Autowired				
	private UserService userService; 

	@Autowired
	ConsumerBannerService bannerService;
	
	/**
	 * @Note:Oluşturulan realty kayıt edilir.
	 * @param realty
	 */
	public void createRealty(Realty realty) {
		
		if (!checkRealty(realty)) {
			return;
		}
		
		/** @Note: Banner Service'i her realty oluşturuldugunda çagıracak*/
		bannerService.create(new Banner(realty.getId(), 1, "usernumber", null));
		
		realty.getUser().getRealtyList().add(realty);
		realtyRepository.saveRealty(realty);
	}

	/**
	 * @Note: girilen realty silinir
	 * @param realty
	 */
	public void deleteRealty(Integer realtyId) {
		Realty findRealtyById = realtyRepository.findRealtyByRealtyId(realtyId);
		if (Objects.nonNull(findRealtyById)) {
			realtyRepository.deleteRealty(findRealtyById);
		}
	}

	/**
	 * @Note: butun realty'leri verir
	 * @return
	 */
	public List<Realty> getAll() {
		return realtyRepository.findAll();
	}

	/**
	 * @Note: Butun ilanları yazdırır.
	 * 
	 */
	public void printAll() {
		getAll().forEach(System.out::println);
	}

	/**
	 * @Note: Girilen realtyId'sine ait Realty'i doner
	 * @param realtyId
	 * @return
	 */
	public Realty findRealtyByRealtyId(Integer realtyId) {
		return realtyRepository.findRealtyByRealtyId(realtyId);
	}

	/**
	 * @Note:Email'i girilen kullanıcıya ait realtyleri verir.
	 * @param email
	 * @return
	 */
	public void printAllByUserEmail(String email) {
		realtyRepository.getRealtyByUserMail(email).forEach(System.out::println);
	}

	/**
	 * @Note: Girilen il veya iller 'de bulunan ilanları verir
	 * @param province
	 * @return
	 */
	public List<Realty> findAllRealtyByProvince(String... province) {
		return realtyRepository.findRealtyByProvince(province);
	}

	/**
	 * @Note: girilen il veya iller'de bulunan aktif ilanları verir
	 * @param province
	 */
	public List<Realty> findActiveRealtyByProvince(String... province) {
		return realtyRepository.findActiveRealtyByProvince(province);
	}

	/**
	 * @Note: Girilen il veya iller 'de bulunan satılık ilanları verir
	 * @param province
	 * @return
	 */
	public List<Realty> findSaleRealtyByProvince(String... province) {
		return realtyRepository.findSaleRealtyByProvince(province);
	}

	/**
	 * @Note: girilen il ve ilçeye göre bulunan Realty'leri verir
	 * @param province
	 * @param district
	 * @return
	 */
	public List<Realty> findRealtyByProvinceAndDistrict(String province, String... district) {
		return realtyRepository.findRealtyByProvinceAndDistrict(province, district);
	}

	/**
	 * @Note: Girilen ile ait vitrini verir.
	 * @return
	 */
	public List<Realty> provinceShowcase(String province) {
		List<String> provinceList = provinceList();
		System.out.println(provinceList);
		if (!provinceList.contains(province.toLowerCase())) {
			System.err.println("girilen il bulunamadı !");
			return Collections.emptyList();
		}
		return realtyRepository.provinceShowcase(provinceList).get(province.toLowerCase());
	}

	/**
	 * @Note: sistemde bulunan iller bulunur
	 * @return
	 */
	private List<String> provinceList() {
		return realtyRepository.findAll().stream().map(it -> it.getProvince().toLowerCase()).distinct().toList();
	}

	/**
	 * @Note : kullanıcının INDIVIDUAL tipinde ise belirli işlemler yapılır Maksimum
	 *       3 Realty verme ve konut tipi harici Realty vermeme.
	 * @param realty
	 * @return
	 */
	private boolean checkRealty(Realty realty) {
		User user = userService.findById(realty.getUserId());
		
		if (Objects.isNull(user)) {
			System.err.println("Girilen Id'ye ait kullanıcı bulunamadı!");
			return false;
		}
		realty.setUser(user);
		if (UserType.INDIVIDUAL.equals(realty.getUser().getType())) {
			if (realty.getUser().getRealtyList().size() > 2) {
				System.err.println("İlanınız kaydedilmedi!. Bireysel kullanıcı tipi maksimum 3 ilan verilebilir");
				return false;
			} else if (PropertyType.COMMERCIAL.equals(realty.getPropertyType())) {
				System.err.println("İlanınız kaydedilmedi!.Commercial tipinde ilan veremezsiniz!");
				return false;
			}
		}

		return true;
	}

}
