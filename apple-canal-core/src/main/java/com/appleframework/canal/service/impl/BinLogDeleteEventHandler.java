package com.appleframework.canal.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.alibaba.otter.canal.protocol.Message;
import com.appleframework.canal.enums.DatabaseEvent;
import com.appleframework.canal.model.DeleteRowsDTO;
import com.appleframework.canal.model.EventBaseDTO;
import com.appleframework.canal.model.FlatMessageJson;
import com.appleframework.canal.service.BinLogEventHandler;
import com.appleframework.canal.util.JsonUtil;

@Service
public class BinLogDeleteEventHandler extends BinLogEventHandler {

	@Override
	public EventBaseDTO formatData(Message message) {
		return null;
	}

	@Override
	public EventBaseDTO formatData(FlatMessage message) {
		DeleteRowsDTO deleteRowsDTO = new DeleteRowsDTO();
		deleteRowsDTO.setEventType(DatabaseEvent.UPDATE_ROWS);
		deleteRowsDTO.setDatabase(message.getDatabase());
		deleteRowsDTO.setTable(message.getTable());
		deleteRowsDTO.setRowMaps(message.getData());
		return deleteRowsDTO;
	}

	@Override
	public EventBaseDTO formatData(FlatMessageJson message) {
		DeleteRowsDTO deleteRowsDTO = new DeleteRowsDTO();
		deleteRowsDTO.setEventType(DatabaseEvent.UPDATE_ROWS);
		deleteRowsDTO.setDatabase(message.getDatabase());
		deleteRowsDTO.setTable(message.getTable());
		deleteRowsDTO.setRowMaps(JsonUtil.arrayToList(message.getData()));
		return deleteRowsDTO;
	}

}