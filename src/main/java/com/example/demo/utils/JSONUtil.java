package com.example.demo.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;

public class JSONUtil {

	public static String toJsonString(Object o){
		  String jsonString = JSONObject.toJSONString(o, new PascalNameFilter());
		  return jsonString;
	}
 
}