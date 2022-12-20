package com.emlakcepte.model;

import java.time.LocalDateTime;

import com.emlakcepte.model.enums.SalesType;
import com.emlakcepte.model.enums.PropertyType;
import com.emlakcepte.model.enums.RealtyType;
import com.emlakcepte.model.utils.IdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Realty {
	
	private Long id;
	private String title;
	private LocalDateTime publishedDate;
	private User user;
	private Integer userId;
	private String province;
	private String district;
	private RealtyType status;
	private SalesType categoryType;
	private PropertyType propertyType;
	
	public Realty(Integer userId) {
		this("BAŞLIK GİRİNİZ",userId,RealtyType.PASSIVE,SalesType.SALE,PropertyType.RESIDENTAL);
	}
	
	public Realty(String title, Integer userId, RealtyType status, SalesType categoryType, PropertyType propertyType) {
		this.id = IdGenerator.getRealtyIdGenerator();
		this.title = title;
		this.publishedDate = LocalDateTime.now();
		this.userId =  userId;
		this.status = status;
		this.propertyType = propertyType;
		this.categoryType = categoryType;
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDateTime publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public RealtyType getStatus() {
		return status;
	}

	public void setStatus(RealtyType status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}
	
	public void setDistrict(String district) {
		this.district = district;
	}

	public SalesType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(SalesType categoryType) {
		this.categoryType = categoryType;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Realty [no=" + id + ", title=" + title + ", status=" + status + ", province="
				+ province + ", district=" + district + "]";
	}

}
