package com.appleframework.canal.model;

import java.util.Map;

import com.appleframework.canal.enums.DatabaseEvent;

public class EventDataDTO extends EventBaseDTO {
	
	private static final long serialVersionUID = 1L;

	private Map<String, String> before;
	private Map<String, String> data;

	public EventDataDTO() {
		super();
	}

	public EventDataDTO(DatabaseEvent eventType, String database, String table) {
		super(eventType, database, table);
	}

	public EventDataDTO(EventBaseDTO eventBaseDTO) {
		super(eventBaseDTO);
	}
	
	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public Map<String, String> getBefore() {
		return before;
	}

	public void setBefore(Map<String, String> before) {
		this.before = before;
	}
    
}
