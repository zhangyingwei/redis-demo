package com.zhangyw.redis.main;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.zhangyw.redis.util.JSONUtil;
import com.zhangyw.redis.util.RedisClient;

public class App {
	public static void main(String[] args) {
		JSONObject urlAndApp = JSONUtil.loadUrlAndAppJson();
		RedisClient client = RedisClient.getIS();
		client.init();
//		String result = client.setValue("urlandapp", urlAndApp.toString());
//		System.out.println(result);
		System.out.println(client.getValue("aaa"));
		client.close();
	}
}
