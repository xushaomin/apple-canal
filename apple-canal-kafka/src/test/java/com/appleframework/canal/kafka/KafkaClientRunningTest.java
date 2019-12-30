package com.appleframework.canal.kafka;

/**
 * Kafka consumer获取Message的测试例子
 *
 * @author machengyuan @ 2018-6-12
 * @version 1.0.0
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
