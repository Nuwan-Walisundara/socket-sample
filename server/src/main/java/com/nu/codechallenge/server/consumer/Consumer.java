package com.nu.codechallenge.server.consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nu.codechallenge.server.MessageHolder;
import com.nu.codechallenge.server.util.ServerConFigDTO;
import com.nuwan.codechallenge.model.Message;

public class Consumer implements Runnable{
	private static Consumer instance;
	private ServerConFigDTO serverConfigDTO;
	private MessageHolder messageHolder;
	private ExecutorService executorService;
	final static Log logger = LogFactory.getLog( Consumer.class);
	
	
	private Consumer(ServerConFigDTO config) {
		this.serverConfigDTO = config;
		messageHolder = MessageHolder.getInstance(serverConfigDTO);
		executorService = Executors.newFixedThreadPool(config.getNumberOfProducers());
	}

	public static Consumer getInstance(ServerConFigDTO serverConfigDTO) {
		
		if(instance==null) {
			instance = new Consumer(serverConfigDTO);
		}
		return instance;
	}
	
	public void run() {			
		while(true) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						Message msg = messageHolder.enqueue();
						new MailSender(serverConfigDTO).sendMail(msg);
					} catch (MessagingException | InterruptedException e) {
						logger.error("",e);
					}	
				}
			});
		
		}
		
	}
	

}
