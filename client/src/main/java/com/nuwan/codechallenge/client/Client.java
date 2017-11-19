package com.nuwan.codechallenge.client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nuwan.codechallenge.ConfigReader;
import com.nuwan.codechallenge.client.util.ClientConFigDTO;
import com.nuwan.codechallenge.client.util.MailUtil;
import com.nuwan.codechallenge.model.Message;



public class Client {

	final static Log logger = LogFactory.getLog( Client.class);

	public static void main(String[] args) {
		logger.info("Starting Sample Email Sender Client ...");

		try {
			final  ClientConFigDTO clientConFigDTO=ConfigReader.getInstance().read(args[0], ClientConFigDTO.class);



			ExecutorService fixedPool = Executors.newFixedThreadPool(clientConFigDTO.getNuberThreads());
			
			int numberOfRequest = clientConFigDTO.getNuberThreads()*clientConFigDTO.getNumberOfRequests();
			
			for (int i = 0; i < numberOfRequest; i++) {
				Message sendMailRequest = MailUtil.buildMessage();
				fixedPool.execute(new RequestSender(sendMailRequest,clientConFigDTO));
			}

			fixedPool.shutdown();
			while (!fixedPool.isTerminated()) {
			}
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | IOException e) {
			logger.error("Unable to start the client ",e);
		}
	}

	
}
