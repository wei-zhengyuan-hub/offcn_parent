package com.offcn.webui.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @version 0.0.3
 * @description RedisLockImpl
 * @since 2020/12/11 17:03
 */
@Component
public class RedisLockImpl {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();//线程安全问题，线程复用

    /**
     * 上锁
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean tryLock(String key, long timeout, TimeUnit unit){

        String uuid = UUID.randomUUID().toString();//用uuid进行唯一标识当前锁
        threadLocal.set(uuid);
        boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(key,uuid,timeout,unit);
        while (!lock){
            lock = stringRedisTemplate.opsForValue().setIfAbsent(key,uuid,timeout,unit);
            if (lock){
                break;
            }
        }
        return lock;
    }

    /**
     * 解锁
     * @param key
     */
    public void releaseLock(String key){
        if (threadLocal.get().equals(stringRedisTemplate.opsForValue().get(key))){

            stringRedisTemplate.delete(key);
        }
    }


}
