package com.Stevens.Rentdata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="zillowrentinfo")
public class RentInfo {
	@Id
	private String Id;
	private String[] http_url;
	private String[] price;
	private String[] house_details;
	private String[] address;
	public String[] getHttp_url() {
		return http_url;
	}
	public void setHttp_url(String[] http_url) {
		this.http_url = http_url;
	}
	public String[] getPrice() {
		return price;
	}
	public void setPrice(String[] price) {
		this.price = price;
	}
	public String[] getHouse_details() {
		return house_details;
	}
	public void setHouse_details(String[] house_details) {
		this.house_details = house_details;
	}
	public String[] getAddress() {
		return address;
	}
	public void setAddress(String[] address) {
		this.address = address;
	}

}
