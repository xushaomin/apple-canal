package com.appleframework.canal.service;

import java.util.List;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.alibaba.otter.canal.protocol.Message;
import com.appleframework.canal.handler.EventDataHandlerFactory;
import com.appleframework.canal.model.EventBaseDTO;
import com.appleframework.canal.model.EventDataDTO;
import com.appleframework.canal.model.FlatMessageJson;

public abstract class BinLogEventHandler {
	
	/**
	 * 处理event
	 *
	 * @param event
	 */
	public void handle(FlatMessage message) {
		EventBaseDTO eventBaseDTO = formatData(message);
		if(null != eventBaseDTO) {
			EventDataHandlerFactory.handle(eventBaseDTO);
		}
	}
	
	public void handle(FlatMessageJson message) {
		EventBaseDTO eventBaseDTO = formatData(message);
		if(null != eventBaseDTO) {
			EventDataHandlerFactory.handle(eventBaseDTO);
		}
	}
	
	public void handle(Message message) {
		EventBaseDTO eventBaseDTO = formatData(message);
		if(null != eventBaseDTO) {
			EventDataHandlerFactory.handle(eventBaseDTO);
		}
	}
	
	public List<EventDataDTO> formatEventData(FlatMessageJson message) {
		EventBaseDTO eventBaseDTO = formatData(message);
		if(null != eventBaseDTO) {
			return EventDataHandlerFactory.formatEventData(eventBaseDTO);
		}
		return null;
	}

	/**
	 * 格式化参数格式
	 *
	 * @param event
	 * @return 格式化后的string
	 */
	public abstract EventBaseDTO formatData(FlatMessage message);
	
	/**
	 * 格式化参数格式
	 *
	 * @param event
	 * @return 格式化后的string
	 */
	public abstract EventBaseDTO formatData(FlatMessageJson message);

	
	/**
	 * 格式化参数格式
	 *
	 * @param event
	 * @return 格式化后的string
	 */
	public abstract EventBaseDTO formatData(Message message);
	
	/**
     * 筛选出关注某事件的应用列表
     * @param event
     * @return
     */
    protected boolean filter(String database, String table) {
    	if(ClientServiceFactory.isExistClient(database, table)) {
			return false;
		}
    	else {
    		return true;
    	}
    }
}