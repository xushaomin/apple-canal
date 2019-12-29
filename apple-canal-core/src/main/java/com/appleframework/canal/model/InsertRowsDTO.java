package com.appleframework.canal.model;

import java.util.List;
import java.util.Map;

import com.appleframework.canal.enums.DatabaseEvent;

public class InsertRowsDTO extends EventBaseDTO {

	private static final long serialVersionUID = 1L;
	
	private List<Map<String, String>> rowMaps;

    public InsertRowsDTO() {
    }

    public InsertRowsDTO(EventBaseDTO eventBaseDTO, List<Map<String, String>> rowMaps) {
        super(eventBaseDTO);
        super.setEventType(DatabaseEvent.INSERT_ROWS);
        this.rowMaps = rowMaps;
    }

    public List<Map<String, String>> getRowMaps() {
        return rowMaps;
    }

    public void setRowMaps(List<Map<String, String>> rowMaps) {
        this.rowMaps = rowMaps;
    }

	@Override
	public String toString() {
		return "WriteRowsDTO{" + "rowMaps=" + rowMaps + "} " + super.toString();
	}
}
