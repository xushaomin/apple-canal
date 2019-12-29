package com.appleframework.canal.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.appleframework.canal.service.impl.BinLogDefaultEventHandler;
import com.appleframework.canal.service.impl.BinLogDeleteEventHandler;
import com.appleframework.canal.service.impl.BinLogUpdateEventHandler;
import com.appleframework.canal.service.impl.BinLogInsertEventHandler;

/**
 * 根据类型提供事件的handler
 */
public class BinLogEventHandlerFactory {

	private static final Logger logger = LoggerFactory.getLogger(BinLogDefaultEventHandler.class);

	private static BinLogUpdateEventHandler binLogUpdateEventHandler = new BinLogUpdateEventHandler();

	private static BinLogInsertEventHandler binLogWriteEventHandler = new BinLogInsertEventHandler();

	private static BinLogDeleteEventHandler binLogDeleteEventHandler = new BinLogDeleteEventHandler();
	
	private static BinLogDefaultEventHandler binLogDefaultEventHandler = new BinLogDefaultEventHandler();
	
	public static BinLogEventHandler getHandler(CanalEntry.EventType eventType) {
		// 考虑到状态映射的问题，只在增删改是更新位置
		if (eventType.name() == CanalEntry.EventType.UPDATE.name()) {
			return binLogUpdateEventHandler;
		} else if (eventType.name() == CanalEntry.EventType.INSERT.name()) {
			return binLogWriteEventHandler;
		} else if (eventType.name() == CanalEntry.EventType.DELETE.name()) {
			return binLogDeleteEventHandler;
		} else {
			logger.debug("不处理事件,{}", eventType);
			return binLogDefaultEventHandler;
		}
	}
	
	public static BinLogEventHandler getHandler(String eventType) {
		// 考虑到状态映射的问题，只在增删改是更新位置
		if (eventType == CanalEntry.EventType.UPDATE.name()) {
			return binLogUpdateEventHandler;
		} else if (eventType == CanalEntry.EventType.INSERT.name()) {
			return binLogWriteEventHandler;
		} else if (eventType == CanalEntry.EventType.DELETE.name()) {
			return binLogDeleteEventHandler;
		} else {
			logger.debug("不处理事件,{}", eventType);
			return binLogDefaultEventHandler;
		}
	}
	
}
