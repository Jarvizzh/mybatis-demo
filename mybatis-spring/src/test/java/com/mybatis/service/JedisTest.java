package com.mybatis.service;

import com.mybatis.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by ZJH on 2017/8/6.
 */
public class JedisTest extends AbstractTest {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate() {
        stringRedisTemplate.opsForValue().set("k6", "6");
        stringRedisTemplate.opsForValue().increment("k6", 660);
//        stringRedisTemplate.boundValueOps("k6").increment(660);
        Map<String, String> map = new HashMap<>();
        map.put("id", "2");
        map.put("name", "z4");
        map.put("sex", "female");
        map.put("email", "123@qq.com");
        stringRedisTemplate.opsForHash().putAll("userZ4", map);
        Map<Object, Object> entry = stringRedisTemplate.opsForHash().entries("userZ3");
    }

    @Test
    public void testExecute() {
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
        String key = "-654584509:2302671359:com.mybatis.mapper.UserMapper.findUserById:0:2147483647:SELECT * FROM user WHERE id = ?:18:SqlSessionFactoryBean";
//        String value = "[User{id=18, name='张三', sex='male', birthday=null, address='aaa', roles=null}]";
//        boolean result = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
//            public Boolean doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                return connection.setNX(serializer.serialize(key), serializer.serialize(value));
//            }
//        });
//        System.out.println(result);

        byte[] value = stringRedisTemplate.execute(new RedisCallback<byte[]>() {

            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(serializer.serialize(key));
            }
        });

        System.out.println(serializer.deserialize(value));
    }

    @Test
    public void testExecute2() {
        byte[] result = stringRedisTemplate.execute(
                new RedisCallback<byte[]>() {
                    @Override
                    public byte[] doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        return connection.get("keyZN".getBytes());
                    }
                }
        );

        System.out.println(new String(result));

        System.out.println(stringRedisTemplate.opsForValue().get("keyZN"));
    }


    /**
     * redis 得到所有的master and slave 信息
     */
    @Test
    public void testGetAllMasterAndSlave() {

        RedisSentinelConnection conn = stringRedisTemplate.getConnectionFactory().getSentinelConnection();

        for (RedisServer master : conn.masters()) {
            System.out.println("master => " + master);// 打印master信息
            Collection<RedisServer> slaves = conn.slaves(master);
            // 打印该master下的所有slave信息
            for (RedisServer slave : slaves) {
                System.out.println("slaves of " + master + " => " + slave);
            }
            System.out.println("--------------");
        }
    }

    @Autowired
    JedisPool jedisPool;

    @SuppressWarnings("unchecked")
    @Test
    public void t(){
        System.out.println(jedisPool.getResource().ping());
    }


}
