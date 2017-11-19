package com.nu.codechallenge.server.prod;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nu.codechallenge.server.util.ServerConFigDTO;

public class Producer  {
	Log log = LogFactory.getLog(Producer.class);
	private static Producer instance;
	private ServerConFigDTO serverConfig;
	private ExecutorService executorService;
	private boolean serverStarted;
	private MsgMarshaller marshaller;
	ServerSocket serverSocket = null;

	private Producer(ServerConFigDTO serverConfig) throws IOException {
		 
		try {
			executorService = Executors.newFixedThreadPool(serverConfig.getNumberOfProducers());
			this.serverStarted = true;
			marshaller = new MsgMarshaller();
			serverSocket = new ServerSocket(serverConfig.getServerPort());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			executorService.shutdown();
		}
	}

	public static Producer getInstance(ServerConFigDTO serverConfig ) throws IOException {

		if (instance == null) {
			instance = new Producer(serverConfig );
		}
		return instance;
	}
	
	private void push(Socket clientSocket) {
		log.debug("mesage push to process quesu");
		
		executorService.execute(new MessageProcessor(clientSocket,serverConfig));
	}	
	
	
	public void listen() {
		log.debug("mesage push to process quesu");
	
		try {
			while (serverStarted) {
				try {
					push(serverSocket.accept());
				} catch (IOException e) {
					log.error("Error occurd but continue listning",e);
				}

			} 
		} finally {
			try {
				serverSocket.close();
				executorService.shutdown();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}