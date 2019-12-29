package com.appleframework.canal.model;

import java.io.Serializable;
import java.util.Map;

public class UpdateRow implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private Map<String, String> beforeRowMap;
    private Map<String, String> afterRowMap;

    public UpdateRow() {

    }

    public UpdateRow(Map<String, String> beforeRowMap, Map<String, String> afterRowMap) {
        this.beforeRowMap = beforeRowMap;
        this.afterRowMap = afterRowMap;
    }

    public Map<String, String> getBeforeRowMap() {
        return beforeRowMap;
    }

    public void setBeforeRowMap(Map<String, String> beforeRowMap) {
        this.beforeRowMap = beforeRowMap;
    }

    public Map<String, String> getAfterRowMap() {
        return afterRowMap;
    }

    public void setAfterRowMap(Map<String, String> afterRowMap) {
        this.afterRowMap = afterRowMap;
    }

	@Override
	public String toString() {
		return "UpdateRow{" + "beforeRowMap=" + beforeRowMap + ", afterRowMap=" + afterRowMap + '}';
	}
}
