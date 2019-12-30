package com.appleframework.canal.kafka;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.otter.canal.client.kafka.KafkaCanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;

/**
 * Kafka client consumer
 *
 * @author cruise.xu @ 2019-12-30
 * @version 0.0.1
 */
public class CanalKafkaClientMessageConsumer implements CanalKafkaClientConsumer {

	protected final static Logger logger = LoggerFactory.getLogger(CanalKafkaClientMessageConsumer.class);

	private KafkaCanalConnector connector;

	private static volatile boolean running = false;

	private Thread thread = null;

	private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
		public void uncaughtException(Thread t, Throwable e) {
			logger.error("parse events has an error", e);
		}
	};
	
	public void start() {
		try {
			connector = new KafkaCanalConnector(
					CanalKafkaClientConfig.getServers(), CanalKafkaClientConfig.getTopic(), 
					CanalKafkaClientConfig.getPartition(), CanalKafkaClientConfig.getGroupId(), 
					CanalKafkaClientConfig.getBatchSize(), false);
			logger.info("## the canal kafka consumer is running now ......");
		} catch (Throwable e) {
			logger.error("## Something goes wrong when starting up the kafka consumer:", e);
			System.exit(0);
		}
	
		Assert.notNull(connector, "connector is null");
		thread = new Thread(new Runnable() {

			public void run() {
				process();
			}
		});
		thread.setUncaughtExceptionHandler(handler);
		thread.start();
		running = true;
	}

	public void stop() {
		if (!running) {
			return;
		}
		running = false;
		if (thread != null) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// ignore
			}
		}
	}

	private void process() {
		while (!running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		while (running) {
			try {
				connector.connect();
				connector.subscribe();
				while (running) {
					try {
						List<Message> messages = connector.getList(100L, TimeUnit.MILLISECONDS);
						if (messages == null || messages.size() == 0) {
							Thread.sleep(5);
							continue;
						}
						for (Message message : messages) {
							long batchId = message.getId();
							int size = message.getEntries().size();
							if (batchId == -1 || size == 0) {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
								}
								logger.warn("the message is error ");
							} else {
								handleMessage(message);
							}
						}
						connector.ack();
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		connector.unsubscribe();
		connector.disconnect();
	}
	
    public void handleMessage(Message message) {
        List<CanalEntry.Entry> entries = message.getEntries();
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType().equals(CanalEntry.EntryType.ROWDATA)) {
                try {

                } catch (Exception e) {
                    throw new RuntimeException("parse event has an error , data:" + entry.toString(), e);
                }
            }
        }
    }
    


	
}
