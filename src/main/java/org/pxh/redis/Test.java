package org.pxh.redis;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @Description
 * @Author pxh
 * @Date 2021/12/20 11:27
 * @Version
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    RedisTemplate redisTemplate ;

    @org.junit.Test
    public  void  test(){
        System.out.println(redisTemplate);
        Boolean aa = redisTemplate.hasKey("aa");
        System.out.println(aa);
        Object object = redisTemplate.opsForValue().get("age");
        System.out.println(object.toString());
        redisTemplate.opsForValue().set("ab",12);
        redisTemplate.opsForValue().setIfAbsent("ab",89);
        System.out.println(redisTemplate.opsForValue().get("ab"));

        Object o = redisTemplate.opsForHash().get("a", "b");
        System.out.println(o);

        Object object9 = redisTemplate.opsForValue().get("age99");
        System.out.println(object9);

        redisTemplate.opsForValue().set("a14",null);
        redisTemplate.expire("a14",10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("a14"));
        System.out.println(redisTemplate.hasKey("a14"));
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisTemplate.hasKey("a14"));

        redisTemplate.opsForValue().set("a15","a",10,TimeUnit.SECONDS);
    }
}