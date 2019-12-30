package com.appleframework.canal.service;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.alibaba.otter.canal.protocol.Message;
import com.appleframework.canal.handler.EventDataHandlerFactory;
import com.appleframework.canal.model.EventBaseDTO;

public abstract class BinLogEventHandler {
	
	/**
	 * 处理event
	 *
	 * @param event
	 */
	public void handle(FlatMessage message) {
		//String database = message.getDatabase();
		//String table = message.getTable();
		//if(!this.filter(database, table)) {
			EventBaseDTO eventBaseDTO = formatData(message);
			if(null != eventBaseDTO) {
				EventDataHandlerFactory.handle(eventBaseDTO);
			}
		//}
	}
	
	//private EventBaseDTO formatEventData(CanalEntry.EventType eventType, FlatMessage message) {
	//	BinLogEventHandler handler = BinLogEventHandlerFactory.getHandler(eventType);
	//	return handler.formatData(message);
	//}

	/**
	 * 格式化参数格式
	 *
	 * @param event
	 * @return 格式化后的string
	 */
	protected abstract EventBaseDTO formatData(FlatMessage message);
	
	/**
	 * 格式化参数格式
	 *
	 * @param event
	 * @return 格式化后的string
	 */
	protected abstract EventBaseDTO formatData(Message message);
	
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