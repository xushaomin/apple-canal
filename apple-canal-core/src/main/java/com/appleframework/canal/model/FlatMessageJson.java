package com.appleframework.canal.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FlatMessageJson {

	private String type;
	private long es;
	private long ts;
	private String database;
	private boolean isDdl;
	private String table;
	private String sql;
	private int id;
	private JSONArray data;
	private JSONArray old;
	private JSONObject sqlType;
	private JSONObject mysqlType;
	private String[] pkNames;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getEs() {
		return es;
	}

	public void setEs(long es) {
		this.es = es;
	}

	public long getTs() {
		return ts;
	}

	public void setTs(long ts) {
		this.ts = ts;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public boolean isDdl() {
		return isDdl;
	}

	public void setDdl(boolean isDdl) {
		this.isDdl = isDdl;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	public JSONArray getOld() {
		return old;
	}

	public void setOld(JSONArray old) {
		this.old = old;
	}

	public JSONObject getSqlType() {
		return sqlType;
	}

	public void setSqlType(JSONObject sqlType) {
		this.sqlType = sqlType;
	}

	public JSONObject getMysqlType() {
		return mysqlType;
	}

	public void setMysqlType(JSONObject mysqlType) {
		this.mysqlType = mysqlType;
	}

	public String[] getPkNames() {
		return pkNames;
	}

	public void setPkNames(String[] pkNames) {
		this.pkNames = pkNames;
	}

}
