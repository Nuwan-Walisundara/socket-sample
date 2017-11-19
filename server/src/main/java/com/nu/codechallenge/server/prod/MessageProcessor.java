package com.nu.codechallenge.server.prod;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nu.codechallenge.server.MessageHolder;
import com.nu.codechallenge.server.util.ServerConFigDTO;
import com.nuwan.codechallenge.model.Message;
import com.nuwan.codechallenge.model.MsgResponse;


public class MessageProcessor implements Runnable {
	private Log log = LogFactory.getLog(MessageProcessor.class);

	private Socket clientSocket;
	private MsgMarshaller marshaller;
	private MsgValidator validator;
	private Acknowlager acknowlager;
	private MessageHolder msgQueue;
	

	MessageProcessor(Socket clientSocket, ServerConFigDTO serverConfig) {
		this.clientSocket = clientSocket;
		marshaller = new MsgMarshaller();
		validator = new MsgValidator();
		acknowlager =new Acknowlager();
		msgQueue =  MessageHolder.getInstance(serverConfig);
		
	}

	public void process() throws Exception {
		log.info("Process started ");
		Message msg = marshaller.marshall(clientSocket);
		MsgResponse response = validator.validate(msg);
		acknowlager.acknowlage(clientSocket, response);
		msgQueue.queue(msg);
		
		
	}

	@Override
	public void run() {
		
		try {
			process();
		} catch (  Exception e) {
			log.error("",e);
		}
	}
}
