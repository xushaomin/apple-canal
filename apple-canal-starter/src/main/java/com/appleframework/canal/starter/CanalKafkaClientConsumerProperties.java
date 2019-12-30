/**
 * Copyright © 2018 organization baomidou
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.appleframework.canal.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * DynamicDataSourceProperties
 *
 * @author Cruise.xu
 * @see CanalKafkaClientConsumerProperties
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = CanalKafkaClientConsumerProperties.PREFIX)
public class CanalKafkaClientConsumerProperties {

	public static final String PREFIX = "apple.canal";

	/**
	 * 订阅的队列名称
	 */
	private String topic = "example";

	/**
	 * Kafka的分区partition个数
	 */
	private Integer partition = null;

	/**
	 * 订阅的groupId
	 */
	private String groupId = null;

	/**
	 * 订阅的地址
	 */
	private String servers = null;

	private Integer batchSize = null;
	
	private Boolean flatMessage = true;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getPartition() {
		return partition;
	}

	public void setPartition(Integer partition) {
		this.partition = partition;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}

	public Boolean getFlatMessage() {
		return flatMessage;
	}

	public void setFlatMessage(Boolean flatMessage) {
		this.flatMessage = flatMessage;
	}

}
