package com.appleframework.canal.kafka;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.otter.canal.client.kafka.KafkaCanalConnector;
import com.alibaba.otter.canal.protocol.FlatMessage;
import com.appleframework.canal.service.BinLogEventHandler;
import com.appleframework.canal.service.BinLogEventHandlerFactory;

/**
 * Kafka client example
 *
 * @author machengyuan @ 2018-6-12
 * @version 1.0.0
 */
public class CanalKafkaClientFlatMessageConsumer implements CanalKafkaClientConsumer {

	protected final static Logger logger = LoggerFactory.getLogger(CanalKafkaClientFlatMessageConsumer.class);

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
					CanalKafkaClientConfig.getBatchSize(), true);
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
						List<FlatMessage> messages = connector.getFlatList(100L, TimeUnit.MILLISECONDS); // 获取message
						if (messages == null) {
							continue;
						}
						for (FlatMessage message : messages) {
							long batchId = message.getId();
							int size = message.getData().size();
							if (batchId == -1 || size == 0) {
								// try {
								// Thread.sleep(1000);
								// } catch (InterruptedException e) {
								// }
								//processMessage(message);
							} else {
								BinLogEventHandler eventHandler = BinLogEventHandlerFactory.getHandler(message.getType());
								eventHandler.handle(message);
								// printSummary(message, batchId, size);
								// printEntry(message.getEntries());
								//logger.info(message.toString());
								//processMessage(message);
							}
						}

						connector.ack(); // 提交确认
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
}
