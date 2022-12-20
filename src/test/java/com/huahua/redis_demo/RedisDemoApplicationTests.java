package com.huahua.redis_demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huahua.redis_demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final ObjectMapper mapper= new ObjectMapper();

    @Test
    void testString() {
        redisTemplate.opsForValue().set("user","huahua");
        redisTemplate.opsForValue().set("age","33");
        Object user = redisTemplate.opsForValue().get("user");
        Object age = redisTemplate.opsForValue().get("age");
        System.out.println("user :"+user +"age "+ age);
    }
    @Test
    void testAddUser(){
        redisTemplate.opsForValue().set("user:1",new User("fabi",10));
       User o = (User)redisTemplate.opsForValue().get("user:1");
        System.out.println(o);
    }
    @Test
    void testString2(){
        stringRedisTemplate.opsForValue().set("student:1","huahua1");
        stringRedisTemplate.opsForValue().set("student:2","alina");
        System.out.println(stringRedisTemplate.opsForValue().get("student:1"));
        System.out.println(stringRedisTemplate.opsForValue().get("student:2"));
    }
    @Test
    void testAddUser1() throws JsonProcessingException {
        User user = new User("wangwu", 23);
        String s = mapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("student:3",s);
        System.out.println(stringRedisTemplate.opsForValue().get("student:3"));
    }
    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("user:400","name","zhangsan");
        stringRedisTemplate.opsForHash().put("user:400","age","32");
        stringRedisTemplate.opsForHash().put("user:400","job","teacher");
        System.out.println(stringRedisTemplate.opsForHash().get("user:400", "job"));
        System.out.println(stringRedisTemplate.opsForHash().entries("user:400"));
    }

}
