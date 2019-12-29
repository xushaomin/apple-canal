package com.appleframework.canal.service;

import java.util.HashSet;
import java.util.Set;

import com.appleframework.canal.model.ClientInfo;

public class ClientServiceFactory {

	private static Set<ClientInfo> set = new HashSet<>();
		
	public static void addClient(ClientInfo clientInfo) {
		set.add(clientInfo);
	}

	public static Set<ClientInfo> listClient() {
		return set;
	}

	public static void deleteClient(ClientInfo clientInfo) {
		set.remove(clientInfo);
	}

	public static boolean isExistClient(ClientInfo clientInfo) {
		return set.contains(clientInfo);
	}

	public static void clearClient() {
		set.clear();
	}
	
	public static boolean isExistClient(String database, String table) {
		ClientInfo clientInfo = new ClientInfo(database, table);
		return set.contains(clientInfo);
	}
	
}