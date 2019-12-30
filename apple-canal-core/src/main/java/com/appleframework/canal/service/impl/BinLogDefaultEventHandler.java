package com.appleframework.canal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.alibaba.otter.canal.protocol.Message;
import com.appleframework.canal.model.EventBaseDTO;
import com.appleframework.canal.model.FlatMessageJson;
import com.appleframework.canal.service.BinLogEventHandler;

@Service
public class BinLogDefaultEventHandler extends BinLogEventHandler {

    private static final Logger log = LoggerFactory.getLogger(BinLogDefaultEventHandler.class);

	@Override
	public EventBaseDTO formatData(FlatMessage message) {
		log.debug("跳过不处理事件message:{}", message);
		return null;
	}

	@Override
	public EventBaseDTO formatData(Message message) {
		log.debug("跳过不处理事件message:{}", message);
		return null;
	}

	@Override
	public EventBaseDTO formatData(FlatMessageJson message) {
		log.debug("跳过不处理事件message:{}", message);
		return null;
	}
	
}
