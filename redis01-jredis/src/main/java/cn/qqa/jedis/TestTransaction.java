package cn.qqa.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","qingo");
        jsonObject.put("age",18);

        jedis.flushDB();

        //开启事务
        Transaction transaction = jedis.multi();

        try {
            transaction.set("user1",jsonObject.toJSONString());
            transaction.set("user2",jsonObject.toJSONString());
            int i = 1/0;//抛出异常，执行失败
            transaction.exec();
        }catch (Exception e){
            e.printStackTrace();
            transaction.discard();//放弃事务
        }finally {
            System.out.println(jedis.get("user1")+"\n"+jedis.get("user2"));
            jedis.close();//关闭连接
        }



    }

}
