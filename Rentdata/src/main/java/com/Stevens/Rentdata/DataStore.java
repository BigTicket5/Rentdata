package com.Stevens.Rentdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DataStore {
	
	public static String apikey = "";
	public static String dest = "1CastlePointTerrace,Hoboken,NJ";
	public static void main(String args[]) throws UnsupportedEncodingException, IOException
	{
		DataStore ds = new DataStore();
		//RouteInfo s = ds.GetRoute("https://maps.googleapis.com/maps/api/directions/json?","transit","57BeachStAPT2RJerseyCityNJ");
		ds.RentdataSave();
	}
	
	public void RentdataSave() throws UnsupportedEncodingException, IOException
	{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("Beans.xml");
		ZillowDAO rentinfo = new ZillowDAOImpl((MongoOperations) ctx.getBean("myMongoTemplate"));
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader("d:\\address.json"));
		Gson gson = new GsonBuilder().create();
		RentInfo[] ri = gson.fromJson(reader,RentInfo[].class );
		for (RentInfo rt : ri)
		{
			System.out.println(rt.getAddress()[0]);
			if(!(rt.getAddress()[0].charAt(0)<48||rt.getAddress()[0].charAt(0)>57))
			{
				rentinfo.addRentInfo(rt);
				if(rentinfo.queryRouteInfo(rt.getAddress()[0]))
				{
					rentinfo.addRouteInfo(GoogleRoute(rt.getAddress()[0]));
				}
			}
		}
	}
	public List<RouteInfo> GoogleRoute(String ori_address) throws UnsupportedEncodingException, IOException
	{
		List<RouteInfo> res = new ArrayList<RouteInfo>();
		String url="https://maps.googleapis.com/maps/api/directions/json?";
		res.add(GetRoute(url,"driving",ori_address));
		res.add(GetRoute(url,"walking",ori_address));
		res.add(GetRoute(url,"bicycling",ori_address));
		return res;
	}
	public RouteInfo GetRoute(String url, String method,String oriadd ) throws UnsupportedEncodingException, IOException
	{
		String address = oriadd;
		//Google API limits the style of address String
		oriadd = oriadd.replace(" ",",");
		oriadd = oriadd.replace("#",",");
		URL apiurl= new URL(url+"key="+apikey+"&language=en&origin="+oriadd+"&destination="+dest+"&mode="+method);		
		URLConnection uc = apiurl.openConnection();
		BufferedReader inbuff = new BufferedReader(new InputStreamReader(uc.getInputStream(),"UTF-8"));
		JsonParser jsonparser = new JsonParser();
        JsonElement route = jsonparser.parse(inbuff);
        String distance =  route.getAsJsonObject().get("routes").getAsJsonArray().get(0).getAsJsonObject()
        		.get("legs").getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsJsonObject().get("text").getAsString();
        String duration =  route.getAsJsonObject().get("routes").getAsJsonArray().get(0).getAsJsonObject()
        		.get("legs").getAsJsonArray().get(0).getAsJsonObject().get("duration").getAsJsonObject().get("text").getAsString();
        RouteInfo newrtinfo = new RouteInfo(distance,duration,method,address);
        System.out.println(distance+";"+duration);
        return newrtinfo;
	}

}
