package com.appleframework.canal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.alibaba.otter.canal.protocol.Message;
import com.appleframework.canal.enums.DatabaseEvent;
import com.appleframework.canal.model.EventBaseDTO;
import com.appleframework.canal.model.FlatMessageJson;
import com.appleframework.canal.model.UpdateRow;
import com.appleframework.canal.model.UpdateRowsDTO;
import com.appleframework.canal.service.BinLogEventHandler;
import com.appleframework.canal.util.JsonUtil;

@Service
public class BinLogUpdateEventHandler extends BinLogEventHandler {

    @Override
    public EventBaseDTO formatData(Message message) {
        return null;
	}

	@Override
    public EventBaseDTO formatData(FlatMessage message) {
		List<UpdateRow> rows = new ArrayList<UpdateRow>();     
        
		UpdateRow row = new UpdateRow();
        row.setAfterRowMap(message.getData().get(0));
        row.setBeforeRowMap(message.getOld().get(0));
        rows.add(row);
        
        UpdateRowsDTO updateRowsDTO = new UpdateRowsDTO();
        updateRowsDTO.setEventType(DatabaseEvent.UPDATE_ROWS);
        updateRowsDTO.setDatabase(message.getDatabase());
        updateRowsDTO.setTable(message.getTable());
        updateRowsDTO.setRows(rows);
        return updateRowsDTO;
    }

	@Override
	public EventBaseDTO formatData(FlatMessageJson message) {
		List<UpdateRow> rows = new ArrayList<UpdateRow>();     
        
		UpdateRow row = new UpdateRow();
        row.setAfterRowMap(JsonUtil.objectToMap(message.getData().getJSONObject(0)));
        row.setBeforeRowMap(JsonUtil.objectToMap(message.getOld().getJSONObject(0)));
        rows.add(row);
        
        UpdateRowsDTO updateRowsDTO = new UpdateRowsDTO();
        updateRowsDTO.setEventType(DatabaseEvent.UPDATE_ROWS);
        updateRowsDTO.setDatabase(message.getDatabase());
        updateRowsDTO.setTable(message.getTable());
        updateRowsDTO.setRows(rows);
        return updateRowsDTO;
	}

}