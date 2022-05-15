package cn.qqa.jedis;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        //1.new一个Jedis对象
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //2.Redis的所有命令都在Jedis中
        System.out.println(jedis.ping());
    }

}
