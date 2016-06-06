package com.Stevens.Rentdata;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ZillowDAOImpl implements ZillowDAO{
	private MongoOperations mongoOps;
	private static final String RENT_COLLECTION = "zillowrentinfo";
	private static final String ROUTE_COLLECTION = "zillowrouteinfo";
	public ZillowDAOImpl(MongoOperations mongoOps){
	        this.mongoOps=mongoOps;
	}
	public void addRentInfo(RentInfo ri) {
		// TODO Auto-generated method stub
		this.mongoOps.insert(ri, RENT_COLLECTION);
	}

	public void delRentInfo() {
		// TODO Auto-generated method stub
		
	}
	public void addRouteInfo(List<RouteInfo> lri) {
		// TODO Auto-generated method stub
		this.mongoOps.insert(lri, ROUTE_COLLECTION);
	}
	public boolean queryRouteInfo(String address) {
		// TODO Auto-generated method stub
		Query query  = new Query();
		query.addCriteria(Criteria.where("address").is(address));
		long count = this.mongoOps.count(query, RouteInfo.class);
		return count==0;
	}

}
