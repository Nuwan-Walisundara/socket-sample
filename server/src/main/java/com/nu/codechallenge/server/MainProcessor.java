package com.nu.codechallenge.server;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.nu.codechallenge.server.consumer.Consumer;
import com.nu.codechallenge.server.prod.Producer;
import com.nu.codechallenge.server.util.ServerConFigDTO;
import com.nuwan.codechallenge.ConfigReader;

public class MainProcessor {
	final static Logger logger = Logger.getLogger(MainProcessor.class);

	public static void main(String[] args) {
		logger.info("Starting EmailSender Server....");
		boolean serverStarted = true;
		try {
			String path=null;
			
			/**
			 * user can provide its own settings as vm arguments . default settins are taken if null
			 */
			if(args.length==0){
				URL url=	MainProcessor.class.getResource("default-config.config");
				path= url.getPath();
			}else{
				path =args[0];
			}
		/**
		 * convert the property file into serverconfigurations
		 */
			final ServerConFigDTO serverConfig = ConfigReader.getInstance().read(path, ServerConFigDTO.class);

			/**
			 * start the consumer threads
			 */
			Thread conumer = new Thread(Consumer.getInstance(serverConfig));
			conumer.start();
			
			/**
			 * start the message queue
			 */
			MessageHolder.start(serverConfig);
			
			/**
			 * start message producer and continue listning on port
			 */
			
			Producer producer = Producer.getInstance(serverConfig);
			producer.listen();

		} catch (InstantiationException | IllegalAccessException | IOException e) {
			logger.error("Unable to start the server ", e);
			System.exit(-1);
		}

	}

}
