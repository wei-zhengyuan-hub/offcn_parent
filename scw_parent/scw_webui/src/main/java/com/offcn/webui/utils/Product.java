package com.offcn.webui.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @version 0.0.3
 * @description Product
 * @since 2020/12/11 17:19
 */
@Controller
public class Product {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisLock redisLock;


    /**
     * 秒杀扣减库存
     */
    public void cart(){
        String product=stringRedisTemplate.opsForValue().get("stock");

        boolean lock = redisLock.tryLock(product, 30, TimeUnit.HOURS);

        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                stock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock",stock+"");
                System.out.println("库存扣减成功，stock"+stock);
            }else {
                System.out.println("库存扣减失败");
            }
        }finally {
            redisLock.releaseLock(product);
        }

    }

}
