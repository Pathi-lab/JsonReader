package com.json.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

	public void readJson() {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			
			obj = parser.parse(new FileReader("C:\\Users\\Sweet Home 2\\Downloads\\config.json"));
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray env = (JSONArray) jsonObject.get("Environment");
			// pass an environment to get its hostname
			getHostname(env, "prod");
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}
	
	// Dynamically gets the hostanme based on environment provided
	public String getHostname(JSONArray jsonArray,String environment) {
		@SuppressWarnings("rawtypes")
		Iterator envIterator = jsonArray.iterator();
		JSONArray envArr = null;
		String hostname = null;
        while (envIterator.hasNext()) {
        	JSONObject envObj = (JSONObject) envIterator.next();
        	if(envObj.containsKey(environment)) {
        	envArr = (JSONArray) envObj.get(environment);
        	JSONObject env = (JSONObject) envArr.get(0);
        	hostname = (String) env.get("hostname");
        	System.out.println("Environment is: "+ environment + " and its Hostname is: "+ hostname);
        	break;
        	}
        	
        }
        return hostname;
	}

	
	public static void main(String[] args) {

		JSONReader jsonReader = new JSONReader();
		jsonReader.readJson();
		
		
	}

}
