package com.appleframework.canal.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.appleframework.canal.enums.DatabaseEvent;
import com.appleframework.canal.model.DeleteRowsDTO;
import com.appleframework.canal.model.EventBaseDTO;
import com.appleframework.canal.model.EventDataDTO;
import com.appleframework.canal.model.UpdateRow;
import com.appleframework.canal.model.UpdateRowsDTO;
import com.appleframework.canal.model.InsertRowsDTO;

@Service
public class EventDataHandlerFactory {

	private static final Logger log = LoggerFactory.getLogger(EventDataHandlerFactory.class);
	
	private static Map<String, List<EventDataHandler>> handlerMap = new HashMap<String, List<EventDataHandler>>();

	public static void handle(EventBaseDTO data) {
		String database = data.getDatabase();
		String table = data.getTable();
		if (data instanceof UpdateRowsDTO) {
			UpdateRowsDTO updateData = (UpdateRowsDTO) data;
			List<UpdateRow> list = updateData.getRows();
			for (UpdateRow updateRow : list) {
				EventDataDTO dto = new EventDataDTO(DatabaseEvent.UPDATE_ROWS, database, table);
				dto.setData(updateRow.getAfterRowMap());
				dto.setBefore(updateRow.getBeforeRowMap());
				execute(database, table, dto);
			}
		} else if (data instanceof InsertRowsDTO) {
			InsertRowsDTO insertData = (InsertRowsDTO) data;
			List<Map<String, String>> list = insertData.getRowMaps();
			for (Map<String, String> mdata : list) {
				EventDataDTO dto = new EventDataDTO(DatabaseEvent.INSERT_ROWS, database, table);
				dto.setData(mdata);
				execute(database, table, dto);
			}
		} else if (data instanceof DeleteRowsDTO) {
			DeleteRowsDTO deleteData = (DeleteRowsDTO) data;
			List<Map<String, String>> list = deleteData.getRowMaps();
			for (Map<String, String> mdata : list) {
				EventDataDTO dto = new EventDataDTO(DatabaseEvent.DELETE_ROWS, database, table);
				dto.setData(mdata);
				execute(database, table, dto);
			}
		} else {
			log.debug(data.toString());
		}
	}
	
	public static void execute(String database, String table, EventDataDTO dto) {
		String key = getHandlerKey(database, table);
		List<EventDataHandler> handlerList = handlerMap.get(key);
		if(null != handlerList && handlerList.size() > 0) {
			for (EventDataHandler eventDataHandler : handlerList) {
				eventDataHandler.handle(dto);
			}
		}
	}
	
	public static List<EventDataDTO> formatEventData(EventBaseDTO data) {
		String database = data.getDatabase();
		String table = data.getTable();
		List<EventDataDTO> dtoList = new ArrayList<EventDataDTO>();
		if (data instanceof UpdateRowsDTO) {
			UpdateRowsDTO updateData = (UpdateRowsDTO) data;
			List<UpdateRow> list = updateData.getRows();
			for (UpdateRow updateRow : list) {
				EventDataDTO dto = new EventDataDTO(DatabaseEvent.UPDATE_ROWS, database, table);
				dto.setData(updateRow.getAfterRowMap());
				dto.setBefore(updateRow.getBeforeRowMap());
				dtoList.add(dto);
			}
		} else if (data instanceof InsertRowsDTO) {
			InsertRowsDTO insertData = (InsertRowsDTO) data;
			List<Map<String, String>> list = insertData.getRowMaps();
			for (Map<String, String> mdata : list) {
				EventDataDTO dto = new EventDataDTO(DatabaseEvent.INSERT_ROWS, database, table);
				dto.setData(mdata);
				dtoList.add(dto);
			}
		} else if (data instanceof DeleteRowsDTO) {
			DeleteRowsDTO deleteData = (DeleteRowsDTO) data;
			List<Map<String, String>> list = deleteData.getRowMaps();
			for (Map<String, String> mdata : list) {
				EventDataDTO dto = new EventDataDTO(DatabaseEvent.DELETE_ROWS, database, table);
				dto.setData(mdata);
				dtoList.add(dto);
			}
		} else {
			log.debug(data.toString());
		}
		return dtoList;
	}
	
	public static void addHandler(String database, String table, EventDataHandler handler) {
		String key = getHandlerKey(database, table);
		List<EventDataHandler> list = handlerMap.get(key);
		if(null == list) {
			list = new ArrayList<EventDataHandler>();
		}
		list.add(handler);
		handlerMap.put(key, list);
	}
	
	private static String getHandlerKey(String database, String table) {
		return database + ":" + table;
	}

}