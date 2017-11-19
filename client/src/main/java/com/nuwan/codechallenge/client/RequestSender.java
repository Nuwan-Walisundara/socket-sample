package com.nuwan.codechallenge.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.nuwan.codechallenge.Constants;
import com.nuwan.codechallenge.MessageHealper;
import com.nuwan.codechallenge.client.util.ClientConFigDTO;
import com.nuwan.codechallenge.model.Message;
import com.nuwan.codechallenge.model.MsgResponse;

public class RequestSender implements Runnable {
	
	final static Log logger = LogFactory.getLog(RequestSender.class);

	private Message sendMailRequest;
	private ClientConFigDTO clientConFigDTO;
	
	public RequestSender(Message sendMailRequest,ClientConFigDTO clientConFigDTO) {
		this.sendMailRequest = sendMailRequest;
		this.clientConFigDTO=clientConFigDTO;
	}

	public void run() {

		Socket socketClient = null;

		try {
			socketClient = new Socket(clientConFigDTO.getHost() ,clientConFigDTO. getPort());
			sendEmailRequest(socketClient, getSendMailRequest());
			readAcknowledgement(socketClient);
		} catch (Exception e) {
			logger.error("Error occured",e );
		} finally {

			if (socketClient != null) {
				try {
					socketClient.close();
				} catch (IOException e) {
					logger.error("Error occured",e );
				}
			}

		}

	}
	
	private void sendEmailRequest(Socket socketClient, Message sendMailRequest) throws IOException {
		String json = MessageHealper.toJson(sendMailRequest) ;
		socketClient.getOutputStream().write(json.getBytes(Constants.ENCODING));
		socketClient.getOutputStream().flush();
		socketClient.shutdownOutput();
		logger.info("Email JSON message sent:" + sendMailRequest.toString());
	}
	
	private void readAcknowledgement(Socket socketClient) throws IOException {
		InputStream inputStream = socketClient.getInputStream();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int readBytes = -1;

		while ((readBytes = inputStream.read(buffer)) > 1) {
			byteArrayOutputStream.write(buffer, 0, readBytes);
		}

		String response = byteArrayOutputStream.toString();
		byteArrayOutputStream.close();
		socketClient.shutdownInput();

		MsgResponse responseObject = MessageHealper.jsonToObject(response, MsgResponse.class);
		
		logger.info(responseObject.toString());
	}


	public Message getSendMailRequest() {
		return sendMailRequest;
	}

	public void setSendMailRequest(Message sendMailRequest) {
		this.sendMailRequest = sendMailRequest;
	}

}
