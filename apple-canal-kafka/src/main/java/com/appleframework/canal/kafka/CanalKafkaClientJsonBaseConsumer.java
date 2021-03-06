package com.appleframework.canal.kafka;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import com.alibaba.fastjson.JSONObject;
import com.appleframework.canal.model.EventDataDTO;
import com.appleframework.canal.model.FlatMessageJson;
import com.appleframework.canal.service.BinLogEventHandler;
import com.appleframework.canal.service.BinLogEventHandlerFactory;

/**
 * Kafka client consumer
 *
 * @author cruise.xu @ 2019-12-30
 * @version 0.0.1
 */
public abstract class CanalKafkaClientJsonBaseConsumer {

	protected final static Logger logger = LoggerFactory.getLogger(CanalKafkaClientJsonBaseConsumer.class);
	
	public abstract void onMessage(EventDataDTO dto);
	
	@KafkaListener(topics = "#{'${spring.kafka.consumer.topics}'.split(',')}", concurrency = "${apple.canal.consumer.concurrency:1}")
	public void run(String message) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("message: ", message);
			}
	        FlatMessageJson flatMessageJson = JSONObject.parseObject(message, FlatMessageJson.class);
			BinLogEventHandler eventHandler = BinLogEventHandlerFactory.getHandler(flatMessageJson.getType());
			List<EventDataDTO> list = eventHandler.formatEventData(flatMessageJson);
			for (EventDataDTO dto : list) {
				onMessage(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
