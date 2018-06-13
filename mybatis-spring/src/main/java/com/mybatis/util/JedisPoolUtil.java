package com.mybatis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 获取Jedis连接池单例工具类
 * Created by ZJH on 2017/8/5.
 */
public class JedisPoolUtil {

    private static volatile JedisPool jedisPool = null;

    private static final String redisHost = "123.207.68.74";
    private static final int redisPort = 6379;

    private JedisPoolUtil() {
    }

    /**
     * 获取JedisPool 实例
     *
     * @return
     */
    public static JedisPool getJedisPoolInstance() {

        if (null == jedisPool) {

            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100 * 1000);
                    poolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(poolConfig, redisHost, redisPort);
                }
            }
        }

        return jedisPool;
    }

    /**
     * 释放资源
     * 将没用的jedis放回jedis池
     *
     * @param jedisPool
     * @param jedis
     */
    public static void release(JedisPool jedisPool, Jedis jedis) {

        if (null != jedis) {
            jedisPool.returnResourceObject(jedis);
        }
    }


}
