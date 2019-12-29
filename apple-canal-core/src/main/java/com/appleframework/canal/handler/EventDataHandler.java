package com.appleframework.canal.handler;

import com.appleframework.canal.model.EventDataDTO;

public interface EventDataHandler {
	
	void handle(EventDataDTO data);
	
}
