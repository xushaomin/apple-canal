package com.appleframework.canal.model;

import java.util.List;
import java.util.Map;

import com.appleframework.canal.enums.DatabaseEvent;

public class DeleteRowsDTO extends EventBaseDTO {
	
	private static final long serialVersionUID = 1L;

    private List<Map<String, String>> rowMaps;

    public DeleteRowsDTO() {
    }

    public DeleteRowsDTO(EventBaseDTO eventBaseDTO, List<Map<String, String>> rowMaps) {
        super(eventBaseDTO);
        super.setEventType(DatabaseEvent.DELETE_ROWS);
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
		return "DeleteRowsDTO{" + "rowMaps=" + rowMaps + "} " + super.toString();
	}
}
