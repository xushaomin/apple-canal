package com.appleframework.canal.handler;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.appleframework.canal.annotation.Handler;

public class EventDataHandlerRegistService implements ApplicationListener<ContextRefreshedEvent> {
 
   @Override
   public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

       ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
       this.classHandler(applicationContext);

   }
   
   private void classHandler(ApplicationContext applicationContext) {
       Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(Handler.class);

	   for (String handlerName : beansWithAnnotation.keySet()) {

           Object handlerObject = beansWithAnnotation.get(handlerName);
           Class<?> handlerImpClass = handlerObject.getClass();
           Handler handler = handlerImpClass.getAnnotation(Handler.class);

           String database = handler.database();
           String table = handler.table();
           
           if(StringUtils.isNotBlank(database) && StringUtils.isNotBlank(table)) {
        	   if(handlerObject instanceof EventDataHandler) {
        		   EventDataHandler eventDataHanler = (EventDataHandler)handlerObject;
        		   EventDataHandlerFactory.addHandler(database, table, eventDataHanler);
        	   }
           }
       }
   }
   
}
