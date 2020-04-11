package com.itcast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class TestSet {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * set类型的存值
     */
    @Test
    public void setValue() {
        redisTemplate.boundSetOps("nameset").add("曹操");
        redisTemplate.boundSetOps("nameset").add("刘备");
        redisTemplate.boundSetOps("nameset").add("孙权");
    }

    /**
     * set类型的取值
     */
    @Test
    public void getValue() {
        Set members = redisTemplate.boundSetOps("nameset").members();
        System.out.println(members);
    }

    /**
     * 删除其中一个
     */
    @Test
    public void delete() {
        redisTemplate.delete("nameset");
    }
}
