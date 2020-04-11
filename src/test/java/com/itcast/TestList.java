package com.itcast;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-redis.xml")
public class TestList {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 右边压栈后添加的对象排在后面
     */
    @Test
    public void testSetValuel() {
        redisTemplate.boundListOps("namelist1").rightPush("刘备");
        redisTemplate.boundListOps("namelist1").rightPush("关于");
        redisTemplate.boundListOps("namelist1").rightPush("张飞");
    }

    @Test
    public void testGetValue1() {
        //range查询索引，查询寻个数，查询个数为-1，不限个数
        List namelist1 = redisTemplate.boundListOps("namelist1").range(1, -1);
        System.out.println(namelist1);
    }

    /**
     * 左边压栈（后添加的对象排在后面）
     */
    @Test
    public void testSetValue2() {

        redisTemplate.boundListOps("namelist2").leftPush("刘备");
        redisTemplate.boundListOps("namelist2").leftPush("关于");
        redisTemplate.boundListOps("namelist2").leftPush("张飞");

    }

    @Test
    public void testGetValue2() {
        List namelist2 = redisTemplate.boundListOps("namelist2").range(0, 10);
        System.out.println(namelist2);
    }

    /**
     * 查询某个位置的元素
     */
    @Test
    public void testSearchByIndex() {
        String s = (String) redisTemplate.boundListOps("namelist2").index(0);
        System.out.println(s);
    }
    @Test
    public void testDelete(){
        redisTemplate.boundListOps("namelist2").remove(1,"张飞");
    }
}
