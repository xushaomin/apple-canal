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

	private static BinLogInsertEventHandler binLogInsertEventHandler = new BinLogInsertEventHandler();

	private static BinLogDeleteEventHandler binLogDeleteEventHandler = new BinLogDeleteEventHandler();
	
	private static BinLogDefaultEventHandler binLogDefaultEventHandler = new BinLogDefaultEventHandler();
	
	public static BinLogEventHandler getHandler(CanalEntry.EventType eventType) {
		// 考虑到状态映射的问题，只在增删改是更新位置
		if (eventType.name() == CanalEntry.EventType.UPDATE.name()) {
			return binLogUpdateEventHandler;
		} else if (eventType.name() == CanalEntry.EventType.INSERT.name()) {
			return binLogInsertEventHandler;
		} else if (eventType.name() == CanalEntry.EventType.DELETE.name()) {
			return binLogDeleteEventHandler;
		} else {
			logger.debug("不处理事件,{}", eventType);
			return binLogDefaultEventHandler;
		}
	}
	
	public static BinLogEventHandler getHandler(String eventType) {
		// 考虑到状态映射的问题，只在增删改是更新位置
		if (CanalEntry.EventType.UPDATE.name().equalsIgnoreCase(eventType)) {
			return binLogUpdateEventHandler;
		} else if (CanalEntry.EventType.INSERT.name().equalsIgnoreCase(eventType)) {
			return binLogInsertEventHandler;
		} else if (CanalEntry.EventType.DELETE.name().equalsIgnoreCase(eventType)) {
			return binLogDeleteEventHandler;
		} else {
			logger.debug("不处理事件,{}", eventType);
			return binLogDefaultEventHandler;
		}
	}
	
}
