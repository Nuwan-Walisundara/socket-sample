package com.nu.codechallenge.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.nu.codechallenge.server.util.ServerConFigDTO;
import com.nuwan.codechallenge.model.Message;

public class MessageHolder {

	private static MessageHolder instance;
	BlockingQueue<Message> mailQueue;

	private MessageHolder(ServerConFigDTO serverConfigDTO) {

		mailQueue = new LinkedBlockingQueue<Message>(serverConfigDTO.getEmailQueueSize());
	}

	public static MessageHolder getInstance(ServerConFigDTO serverConfigDTO) {
		if (instance == null) {
			instance = new MessageHolder(serverConfigDTO);
		}
		return instance;
	}

	public static void start(ServerConFigDTO serverConfigDTO) {
		if (instance == null) {
			instance = new MessageHolder(serverConfigDTO);
		}
	}
	
	public void queue(Message msg) throws InterruptedException {
		mailQueue.offer(msg, 100, TimeUnit.MILLISECONDS);
	}

	public Message enqueue() throws InterruptedException {
		return mailQueue.take();
	}

}
