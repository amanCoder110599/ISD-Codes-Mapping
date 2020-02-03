package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ISD_code {
	@Id
	private String country;
	private String country_code;
	private String isd;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public ISD_code(String country, String country_code, String isd) {
		this.country = country;
		this.country_code = country_code;
		this.isd = isd;
	}
	public ISD_code() {
		
	}
	@Override
	public String toString() {
		return "ISD_code [country=" + country + ", country_code=" + country_code + ", isd=" + isd + "]";
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getIsd() {
		return isd;
	}
	public void setIsd(String isd) {
		this.isd = isd;
	}
	
}
