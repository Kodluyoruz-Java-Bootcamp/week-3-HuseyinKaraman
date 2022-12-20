package com.emlakcepte.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import com.emlakcepte.model.Realty;
import com.emlakcepte.model.enums.SalesType;
import com.emlakcepte.model.enums.RealtyType;

@Repository
public class RealtyRepository {

	private static List<Realty> realtyList = new ArrayList<>();

	/**
	 * @Note:Oluşturulan realty kayıt edilir.
	 * @param realty
	 */
	public void saveRealty(Realty realty) {
		realtyList.add(realty);
	}

	/**
	 * @Note: girilen realty silinir
	 * @param realty
	 */
	public void deleteRealty(Realty realty) {
		realtyList.remove(realty);
	}

	/**
	 * @Note: butun realty'leri verir
	 * @return
	 */
	public List<Realty> findAll() {
		return realtyList;
	}

	/**
	 * @Note: Girilen realtyId'sine ait Realty'i doner
	 * @param realtyId
	 * @return
	 */
	public Realty findRealtyByRealtyId(Integer realtyId) {
		List<Realty> collect = realtyList.stream().filter(it -> Objects.equals(it.getId().intValue(), realtyId))
				.toList();
		return !collect.isEmpty() ? collect.get(0) : null;
	}

	/**
	 * @Note:Email'i girilen kullanıcıya ait realtyleri verir.
	 * @param email
	 * @return
	 */
	public List<Realty> getRealtyByUserMail(String email) {
		return findAll().stream().filter(realty -> realty.getUser().getMail().equals(email))
				.toList();
	}

	/**
	 * @Note:Girilen il veya iller 'de bulunan ilanları verir
	 * 
	 * @param province
	 * @return
	 */
	public List<Realty> findRealtyByProvince(String... province) {
		return realtyList.stream()
				.filter(it -> Arrays.toString(province).toLowerCase().contains(it.getProvince().toLowerCase()))
				.toList();
	}

	/**
	 * @Note:Girilen il veya iller 'de bulunan aktif ilanları verir
	 * 
	 * @param province
	 * @return
	 */
	public List<Realty> findActiveRealtyByProvince(String... province) {
		return realtyList.stream()
				.filter(it -> Arrays.toString(province).toLowerCase().contains(it.getProvince().toLowerCase()))
				.filter(it -> it.getStatus().equals(RealtyType.ACTIVE)).toList();
	}

	/**
	 * @Note:Girilen il veya iller 'de bulunan satılık ilanları verir
	 * 
	 * @param province
	 * @return
	 */
	public List<Realty> findSaleRealtyByProvince(String... province) {
		return realtyList.stream()
				.filter(it -> Arrays.toString(province).toLowerCase().contains(Objects.nonNull(it.getProvince()) ? it.getProvince().toLowerCase() : it.getProvince()))
				.filter(it -> it.getCategoryType().equals(SalesType.SALE)).toList();
	}

	/**
	 * @Note: girilen il ve ilçeye göre bulunan Realty'leri verir
	 * @param province
	 * @param district
	 * @return
	 */
	public List<Realty> findRealtyByProvinceAndDistrict(String province, String... district) {
		return findRealtyByProvince(province).stream()
				.filter(it -> Arrays.toString(district).toLowerCase().contains(it.getDistrict().toLowerCase())).toList();
	}

	/**
	 * @Note: Her ile ait Vitrin olusturulacak maksimum 10 tane elemana sahip olacak
	 * @return
	 */
	public Map<String, List<Realty>> provinceShowcase(List<String> provinces) {
		Map<String, List<Realty>> showcase = new LinkedHashMap<>();
		for (String province : provinces) {
			List<Realty> findRealtyByProvince = findRealtyByProvince(province);
			showcase.put(province.toLowerCase(),
					findRealtyByProvince.size() > 10 ? findRealtyByProvince.subList(0, 9) : findRealtyByProvince);
		}
		return showcase;
	}

}
