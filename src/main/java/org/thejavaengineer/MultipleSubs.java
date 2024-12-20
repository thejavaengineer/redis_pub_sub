package org.thejavaengineer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class MultipleSubs implements Runnable {

    private String subscriberName;

    public MultipleSubs(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    @Override
    public void run() {
        Jedis jedis = new Jedis("localhost", 6379);  // Connect to Redis

        // Create a new JedisPubSub instance to handle incoming messages
        JedisPubSub pubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(subscriberName + " received: " + message);
            }
        };

        // Subscribe to the channel and start listening for messages
        jedis.subscribe(pubSub, "my_channel");
    }

    public static void main(String[] args) {
        // Create and start multiple subscriber threads
        Thread subscriber1 = new Thread(new MultipleSubs("Subscriber 1"));
        Thread subscriber2 = new Thread(new MultipleSubs("Subscriber 2"));
        Thread subscriber3 = new Thread(new MultipleSubs("Subscriber 3"));

        subscriber1.start();
        subscriber2.start();
        subscriber3.start();
    }
}
