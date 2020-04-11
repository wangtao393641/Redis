package com.itcast;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class TestValue {

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * redis存数据
     */
    @Test
    public void setValue() {
        redisTemplate.boundValueOps("name").set("itcast");
        redisTemplate.boundZSetOps("name").expire(10, TimeUnit.SECONDS);
    }
    /**
     * redis取数据
     */
    @Test
    public void getValue() {
        String str = (String) redisTemplate.boundValueOps("name").get();
        System.out.println(str);
    }
    @Test
    public void deleteValue() {
        redisTemplate.delete("name");

    }
}
