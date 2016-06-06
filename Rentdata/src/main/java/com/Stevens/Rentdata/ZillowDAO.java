package com.Stevens.Rentdata;

import java.util.List;

public interface ZillowDAO {
	public void addRentInfo(RentInfo ri);
	public void addRouteInfo(List<RouteInfo> ri);
	public boolean queryRouteInfo(String address);
	public void delRentInfo();
}
