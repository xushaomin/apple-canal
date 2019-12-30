package com.appleframework.canal.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.appleframework.canal.handler.EventDataHandlerRegistService;

@Configuration
public class EventDataHandlerRegistAutoConfiguration {

	@ConditionalOnMissingBean
	@Bean
    public EventDataHandlerRegistService registAllService(){
        return new EventDataHandlerRegistService();
    }
	
}
