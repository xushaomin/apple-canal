package com.appleframework.canal.kafka;

/**
/**
 * Kafka client consumer
 *
 * @author cruise.xu @ 2019-12-30
 * @version 0.0.1
 */
public class KafkaClientRunningTest {

    public static void main(String[] args) {
    	CanalKafkaClientConfig.setServers("middleware02:9092");
    	CanalKafkaClientConfig.setGroupId("apple");
    	CanalKafkaClientConfig.setTopic("example-2");
    	
    	CanalKafkaClientConsumer consumer = new CanalKafkaClientFlatMessageConsumer();
    	consumer.start();
	}


}
