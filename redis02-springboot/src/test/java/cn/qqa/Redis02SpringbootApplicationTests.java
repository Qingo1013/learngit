package cn.qqa;

import cn.qqa.pojo.User;
import cn.qqa.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {

        //redisTemplate 操作不同数据类型，api与命令类似
        // opsForValue 操作字符串 类似String
        //操作List类型 类似List
        //操作Set类型 类似Set

        //常用方法可以直接通过RedisTemplate调用，比如事务和基本的CRUD

/*
        获取Redis的连接对象
        flushxx相关命令
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.flushDb();
        connection.flushAll();
*/
        redisTemplate.opsForValue().set("key1","qingo");
        System.out.println(redisTemplate.opsForValue().get("key1"));


    }

    @Test
    public void testSeri(){
        User user = new User("qingo", 18);
        try {
            String jsonUser = new ObjectMapper().writeValueAsString(user);
            redisTemplate.opsForValue().set("user",user)    ;
            System.out.println(redisTemplate.opsForValue().get("user"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUtil(){
        redisUtil.set("name","Qingo");
        System.out.println(redisUtil.get("name"));
    }

}
