package com.zhangyw.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisPool {
    
    //IP
    private static final String ADDR = "192.168.58.129";
    //Port
    private static final int PORT = 6379;
    //Password
    private static final String AUTH = "redis";
    //��������ʵ���������Ŀ��Ĭ��ֵΪ8��
    //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
    private static final int MAX_TOTAL = 1024;
    //����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����Ĭ��ֵҲ��8��
    private static final int MAX_IDLE = 200;
    //�ȴ��������ӵ����ʱ�䣬��λ���룬Ĭ��ֵΪ-1����ʾ������ʱ����������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
    private static final int MAX_WAIT = 10000;
    private static final int TIMEOUT = 10000;
    //��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
    private static boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;
    
    /**
     * ��ʼ��Redis���ӳ�
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static class JedisHandler{
    	static Jedis resource = jedisPool.getResource();
    }
    /**
     * ��ȡJedisʵ��
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            return JedisHandler.resource;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * �ͷ�jedis��Դ
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}