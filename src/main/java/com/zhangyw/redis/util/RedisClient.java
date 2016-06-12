package com.zhangyw.redis.util;

import redis.clients.jedis.Jedis;

public class RedisClient {
	
	private RedisClient(){}
	private static class RedisClientHandler{
		static RedisClient client = new RedisClient();
	}
	public static RedisClient getIS(){
		return RedisClientHandler.client;
	}
	
	private Jedis jedis = null;
	public void init(){
//		this.jedis = new Jedis("192.168.58.129", 6379);
//		jedis.auth("redis");
		this.jedis = RedisPool.getJedis();
	}
	
	public String setValue(String key,String value){
		return this.jedis.set(key, value);
	}
	public String getValue(String key){
		return jedis.get(key);
	}
	public void close(){
		RedisPool.returnResource(this.jedis);
	}
}
