package com.appleframework.canal.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.appleframework.canal.model.FlatMessageJson;
import com.appleframework.canal.service.BinLogEventHandler;
import com.appleframework.canal.service.BinLogEventHandlerFactory;

/**
 * Kafka client consumer
 *
 * @author cruise.xu @ 2019-12-30
 * @version 0.0.1
 */
@Component
public class CanalKafkaClientJsonMessageConsumer {

	protected final static Logger logger = LoggerFactory.getLogger(CanalKafkaClientJsonMessageConsumer.class);
		
	@KafkaListener(topics = "#{'${apple.canal.consumer.topics}'.split(',')}", concurrency = "${apple.canal.consumer.concurrency:1}")
	public void run(String message) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("message: ", message);
			}
	        FlatMessageJson flatMessageJson = JSONObject.parseObject(message, FlatMessageJson.class);
			BinLogEventHandler eventHandler = BinLogEventHandlerFactory.getHandler(flatMessageJson.getType());
			eventHandler.handle(flatMessageJson);
		} catch (Exception e) {
			throw e;
		}
	}

}
