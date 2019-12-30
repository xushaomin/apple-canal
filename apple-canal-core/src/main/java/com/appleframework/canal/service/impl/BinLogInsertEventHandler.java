package com.appleframework.canal.service.impl;

import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.alibaba.otter.canal.protocol.Message;
import com.appleframework.canal.enums.DatabaseEvent;
import com.appleframework.canal.model.EventBaseDTO;
import com.appleframework.canal.model.FlatMessageJson;
import com.appleframework.canal.model.InsertRowsDTO;
import com.appleframework.canal.service.BinLogEventHandler;
import com.appleframework.canal.util.JsonUtil;

@Service
public class BinLogInsertEventHandler extends BinLogEventHandler {

	@Override
	public EventBaseDTO formatData(FlatMessage message) {
		InsertRowsDTO insertRowsDTO = new InsertRowsDTO();
		insertRowsDTO.setEventType(DatabaseEvent.INSERT_ROWS);
		insertRowsDTO.setDatabase(message.getDatabase());
		insertRowsDTO.setTable(message.getTable());
		insertRowsDTO.setRowMaps(message.getData());
		return insertRowsDTO;
	}

	@Override
	public EventBaseDTO formatData(Message message) {
		return null;
	}

	@Override
	public EventBaseDTO formatData(FlatMessageJson message) {
		InsertRowsDTO insertRowsDTO = new InsertRowsDTO();
		insertRowsDTO.setEventType(DatabaseEvent.INSERT_ROWS);
		insertRowsDTO.setDatabase(message.getDatabase());
		insertRowsDTO.setTable(message.getTable());
		insertRowsDTO.setRowMaps(JsonUtil.arrayToList(message.getData()));
		return insertRowsDTO;
	}

}