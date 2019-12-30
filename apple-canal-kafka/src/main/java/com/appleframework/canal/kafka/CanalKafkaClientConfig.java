package com.appleframework.canal.kafka;

/**
 * Kafka 测试基类
 *
 * @author machengyuan @ 2018-6-12
 * @version 1.0.0
 */
public class CanalKafkaClientConfig {

	private static String topic = "example";
	private static Integer partition = null;
	private static String groupId = null;
	private static String servers = null;
	private static Integer batchSize = null;

	public static String getTopic() {
		return topic;
	}

	public static void setTopic(String topic) {
		CanalKafkaClientConfig.topic = topic;
	}

	public static Integer getPartition() {
		return partition;
	}

	public static void setPartition(Integer partition) {
		CanalKafkaClientConfig.partition = partition;
	}

	public static String getGroupId() {
		return groupId;
	}

	public static void setGroupId(String groupId) {
		CanalKafkaClientConfig.groupId = groupId;
	}

	public static String getServers() {
		return servers;
	}

	public static void setServers(String servers) {
		CanalKafkaClientConfig.servers = servers;
	}

	public static Integer getBatchSize() {
		return batchSize;
	}

	public static void setBatchSize(Integer batchSize) {
		CanalKafkaClientConfig.batchSize = batchSize;
	}
	
}
