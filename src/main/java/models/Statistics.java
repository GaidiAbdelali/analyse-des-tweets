package models;


import java.util.List;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class Statistics {

	private String name;
	private int firstCount;
	private int secondCount;
	
	public Statistics() {}
	
	public Statistics(String name, int firstCount, int secondCount) {
		super();
		this.name = name;
		this.firstCount = firstCount;
		this.secondCount = secondCount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFirstCount() {
		return firstCount;
	}
	public void setFirstCount(int firstCount) {
		this.firstCount = firstCount;
	}
	public int getSecondCount() {
		return secondCount;
	}
	public void setSecondCount(int secondCount) {
		this.secondCount = secondCount;
	}
	
	public int pourcentageFirstCount(){
		int total = firstCount + secondCount;
		return total != 0 ? (firstCount * 100)/total : 50; 
	}
	
	public int pourcentageSecondCount(){
		int total = firstCount + secondCount;
		return total != 0 ? (secondCount * 100)/total : 50; 
	}
	
	public JSONObject toJson(int team){
		JSONObject object = new JSONObject();
		try {
			object.put("country", name);
			object.put("positive", team == 1 ? firstCount : secondCount);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	public static JSONArray listToJson(List<Statistics> list,int team){
		JSONArray array = new JSONArray();
		list.forEach(s -> array.put(s.toJson(team)));
		
		return array;
	}
	
	public JSONObject toJsonDonut(int team){
		JSONObject object = new JSONObject();
		try {
			object.put("label", name);
			object.put("value", team == 1 ? firstCount : secondCount);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return object;
	}
	
	public static JSONArray listToJsonDonut(List<Statistics> list,int team){
		JSONArray array = new JSONArray();
		list.forEach(s -> array.put(s.toJsonDonut(team)));
		
		return array;
	}
	
}
