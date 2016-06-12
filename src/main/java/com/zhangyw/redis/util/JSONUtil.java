package com.zhangyw.redis.util;

import net.sf.json.JSONObject;

public class JSONUtil {
	public static JSONObject loadUrlAndAppJson(){
//		System.getProperty("user.dir")
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\urlandapp_template.json";
		return loadJson(path);
	}
	public static JSONObject loadJson(String path){
		JSONObject json = new JSONObject();
		String jsonStr = FileUtil.readFile(path);
		if(null!=jsonStr){
			json = JSONObject.fromObject(jsonStr);
		}
		return json;
	}
}
