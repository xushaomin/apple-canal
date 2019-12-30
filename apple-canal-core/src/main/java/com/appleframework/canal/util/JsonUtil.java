package com.appleframework.canal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class JsonUtil {

	public static List<Map<String, String>> arrayToList(JSONArray array) {
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		List<JSONObject> jsonList = array.toJavaList(JSONObject.class);
		for (JSONObject jsonObject : jsonList) {
			returnList.add(objectToMap(jsonObject));
		}
		return returnList;
	}
		
	public static Map<String, String> objectToMap(JSONObject jsonObject) {
		return jsonObject.toJavaObject(new TypeReference<Map<String, String>>(){});
	}
}
