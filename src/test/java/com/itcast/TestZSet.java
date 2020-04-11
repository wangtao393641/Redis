package com.itcast;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class TestZSet {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存值
     */
    @Test
    public void testSetValue() {
        redisTemplate.boundZSetOps("namezset").add("曹操", 10000);
        redisTemplate.boundZSetOps("namezset").add("孙权", 0);
        redisTemplate.boundZSetOps("namezset").add("刘备", 10000);

    }

    /**
     * 取值
     */

    @Test
    public void testGetValue() {
        Set namezset = redisTemplate.boundZSetOps("namezset").range(0, 10);
        System.out.println(namezset);

    }

    /**
     * 土豪棒
     */
    @Test
    public void testTuhaobang() {
        Set namezset = redisTemplate.boundZSetOps("namezset").reverseRange(0, 9);
        System.out.println(namezset);
    }

    /**
     * 增加分数
     */

    @Test
    public void addScore() {
        redisTemplate.boundZSetOps("namezset").incrementScore("孙权", 2000);
        redisTemplate.boundZSetOps("namezset").incrementScore("刘备", 100);
    }

    /**
     * 查询分数与值
     */
    @Test
    public void getValueAndScore() {
        Set<ZSetOperations.TypedTuple> namezset = redisTemplate.boundZSetOps("namezset").reverseRangeWithScores(0, 9);
        for(ZSetOperations.TypedTuple typedTuple:namezset){
            System.out.println("姓名:"+typedTuple.getValue());
            System.out.println("金币数:"+typedTuple.getScore());
        }
    }
}
