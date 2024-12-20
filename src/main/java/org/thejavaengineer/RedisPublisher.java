package org.thejavaengineer;

import redis.clients.jedis.Jedis;

import java.time.LocalTime;

public class RedisPublisher {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        while (true) {
            jedis.publish("my_channel", "Hello Redis!" + LocalTime.now());
            try {
                Thread.sleep(1000);  // Broadcasting every second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
