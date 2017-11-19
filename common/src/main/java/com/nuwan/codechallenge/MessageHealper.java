package com.nuwan.codechallenge;

import com.google.gson.Gson;

public class MessageHealper {

	public static <T> T jsonToObject(String jsonObject,Class<T> classz){
		Gson gsonParser = new Gson();
		return gsonParser.fromJson(jsonObject, classz);
	}
	
	public static String toJson( Object object){
		Gson gsonParser = new Gson();
		return gsonParser.toJson(object);
	}
	
}
