package com.itcast;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class TestHash {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSetValue() {
        redisTemplate.boundHashOps("namehash").put("a", "唐僧");
        redisTemplate.boundHashOps("namehash").put("b", "悟空");
        redisTemplate.boundHashOps("namehash").put("c", "沙和尚");
        redisTemplate.boundHashOps("namehash").put("d", "猪八戒");

    }

    /**
     * 查询所有的key
     */
    @Test
    public void testGetKey() {
        Set keys = redisTemplate.boundHashOps("namehash").keys();
        System.out.println(keys);
    }

    /**
     * 查询所有的存值
     */
    @Test
    public void testGetValue() {
        List namehash = redisTemplate.boundHashOps("namehash").values();
        System.out.println(namehash);
    }

    /**
     * 根据Key查询值
     */
    @Test
    public void testGetValueByKey() {
        Object o = redisTemplate.boundHashOps("namehash").get("b");
        System.out.println(o);
    }

    @Test
    public void testDeleteValuesByKey() {
        redisTemplate.boundHashOps("namehash").delete("c");
    }
}