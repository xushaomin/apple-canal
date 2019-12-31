package com.appleframework.canal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.protocol.CanalEntry;
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
	public EventBaseDTO formatData(Message message) {
		InsertRowsDTO insertRowsDTO = new InsertRowsDTO();
		insertRowsDTO.setEventType(DatabaseEvent.INSERT_ROWS);
		List<Map<String, String>> rowMaps = new ArrayList<Map<String, String>>();

		List<CanalEntry.Entry> entries = message.getEntries();
		for (CanalEntry.Entry entry : entries) {
			if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
				try {
					insertRowsDTO.setDatabase(entry.getHeader().getSchemaName());
					insertRowsDTO.setTable(entry.getHeader().getTableName());

					CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
					List<CanalEntry.RowData> rowDataList = rowChange.getRowDatasList();

					for (CanalEntry.RowData rowData : rowDataList) {
						List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
						Map<String, String> afterMap = afterColumnsList.stream()
								.collect(Collectors.toMap(CanalEntry.Column::getName, CanalEntry.Column::getValue));
						rowMaps.add(afterMap);
					}
				} catch (Exception e) {
					throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
				}
			}
		}
		return insertRowsDTO;
	}
	
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
	public EventBaseDTO formatData(FlatMessageJson message) {
		InsertRowsDTO insertRowsDTO = new InsertRowsDTO();
		insertRowsDTO.setEventType(DatabaseEvent.INSERT_ROWS);
		insertRowsDTO.setDatabase(message.getDatabase());
		insertRowsDTO.setTable(message.getTable());
		insertRowsDTO.setRowMaps(JsonUtil.arrayToList(message.getData()));
		return insertRowsDTO;
	}

}