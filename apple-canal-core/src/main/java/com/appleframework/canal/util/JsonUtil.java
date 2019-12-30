package com.appleframework.canal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	public static List<Map<String, String>> arrayToList(JSONArray array) {
		List<Map<String, String>> returnList = new ArrayList<Map<String,String>>();
		List<JSONObject> jsonList = array.toJavaList(JSONObject.class);
		for (JSONObject jsonObject : jsonList) {
			returnList.add(objectToMap(jsonObject));
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> objectToMap(JSONObject jsonObject) {
		 Map<String, String> jsonMap = JSONObject.toJavaObject(jsonObject, Map.class);
		return jsonMap;
	}
}
