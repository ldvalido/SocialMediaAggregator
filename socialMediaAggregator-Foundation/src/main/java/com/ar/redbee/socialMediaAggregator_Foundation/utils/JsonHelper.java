package com.ar.redbee.socialMediaAggregator_Foundation.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonHelper<T> {

	public static String serialize(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String returnValue = null;
		try {
			returnValue = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public static <T> T desearilize(String jsonString, Class<T> clazz) {
	   T obj = null;
	   ObjectMapper mapper = new ObjectMapper();
	   try {
	     obj = mapper.readValue(jsonString, clazz);
	   } catch (Exception e) {
	     e.printStackTrace();
	   }
	 
	   return obj;
	 }
}