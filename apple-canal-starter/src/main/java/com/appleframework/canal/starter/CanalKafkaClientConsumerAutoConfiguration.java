/**
 * Copyright © 2018 organization appleframework
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.appleframework.canal.kafka.CanalKafkaClientConfig;
import com.appleframework.canal.kafka.CanalKafkaClientConsumer;
import com.appleframework.canal.kafka.CanalKafkaClientFlatMessageConsumer;

/**
 * 动态数据源核心自动配置类
 *
 * @author Cruise.Xu
 * @see CanalKafkaClientConsumer
 * @since 0.0.1
 */

@Configuration
@EnableConfigurationProperties(CanalKafkaClientConsumerProperties.class)
@ConditionalOnProperty(prefix = CanalKafkaClientConsumerProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class CanalKafkaClientConsumerAutoConfiguration {

	@Autowired
	private CanalKafkaClientConsumerProperties properties;
	
	@Bean
	@ConditionalOnMissingBean
    public CanalKafkaClientConsumer registCanalKafkaClientConsumer(){
		
		if(properties.getFlatMessage()) {
	    	CanalKafkaClientConfig.setServers(properties.getServers());
	    	CanalKafkaClientConfig.setGroupId(properties.getGroupId());
	    	CanalKafkaClientConfig.setTopic(properties.getTopic());
	    	CanalKafkaClientConfig.setBatchSize(properties.getBatchSize());
	    	CanalKafkaClientConfig.setPartition(properties.getPartition());
	    	
	    	CanalKafkaClientConsumer consumer = new CanalKafkaClientFlatMessageConsumer();
	    	consumer.start();

	        return consumer;
		}
		else {
			return null;
		}
    }
}
