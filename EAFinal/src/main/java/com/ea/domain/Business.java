package com.ea.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "ROLE_BUSINESS")
public class Business extends User {

	private String name;
	private String businessType;
	private String description;
	private byte[] LogoImage;
	private int longitude;
	private int latitude;
	@OneToMany(mappedBy = "business")
	private List<Item> items = new ArrayList<>();

	public Business(){}

	public Business(String name,String businessType, String description, byte[] logoImage, int longitude, int latitude, List<Item> items) {
		this.name = name;
		this.businessType = businessType;
		this.description = description;
		LogoImage = logoImage;
		this.longitude = longitude;
		this.latitude = latitude;
		this.items = items;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getLogoImage() {
		return LogoImage;
	}

	public void setLogoImage(byte[] logoImage) {
		LogoImage = logoImage;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		return name;
	}
}
