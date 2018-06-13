package com.mybatis.cache;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mybatis.util.JedisPoolUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 使用Redis第三方缓存服务器，处理二级缓存
 */
public class MyRedisCache implements Cache {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

    private String id;
    private JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

    public MyRedisCache() {
    }

    public MyRedisCache(final String id) {
        Assert.notNull(id, "缓存ID不能为空");
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("==============SET OBJ==================" + key.toString() + "====" + value.toString());
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key.toString().getBytes(), serializer.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Override
    public Object getObject(Object key) {
        System.out.println("==============GET OBJ==================" + key.toString());
        Jedis jedis = jedisPool.getResource();
        Object value = null;
        try {
            value = serializer.deserialize(jedis.get(key.toString().getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        System.out.println("==============RMV OBJ==================" + key.toString());
        Jedis jedis = jedisPool.getResource();
        Long result = 0L;
        try {
            result = jedis.del(key.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
        return result;
    }

    @Override
    public void clear() {
        System.out.println("==============CLEAR==================" + this.id);
        String pattern = "*" + this.id + "*";
        Jedis jedis = jedisPool.getResource();
        try {
            Set<byte[]> keys = jedis.keys(pattern.getBytes());
            keys.forEach(jedis::del);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPool.returnResourceObject(jedis);
        }
    }

    @Override
    public int getSize() {
        Jedis jedis = jedisPool.getResource();
        return (int)(long)jedis.dbSize();
//        byte[] pattern = serializer.serialize(this.id + "*");
//        try {
//            Set<byte[]> keys = jedis.keys(pattern);
//            return keys.size();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            jedisPool.returnResourceObject(jedis);
//        }
//        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

}