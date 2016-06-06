package com.Stevens.Rentdata;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="zillowrouteinfo")
public class RouteInfo {
	@Id
	private String Id;
	private String address;
	private String distance;
	private String duration;
	private String method;
	public RouteInfo(String dis,String dur,String method,String add)
	{
		this.distance = dis;
		this.duration = dur;
		this.method = method;
		this.address = add;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
