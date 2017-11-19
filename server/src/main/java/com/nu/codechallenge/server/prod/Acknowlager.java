package com.nu.codechallenge.server.prod;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.nuwan.codechallenge.model.MsgResponse;

public class Acknowlager {
	Log log = LogFactory.getLog(Acknowlager.class);

	public void acknowlage(Socket clientSocket,MsgResponse response) throws IOException {


		Gson gsonParser = new Gson();
		String json =  gsonParser.toJson(response);

			clientSocket.getOutputStream().write(json.getBytes("UTF-8" ));
			clientSocket.getOutputStream().flush();
			clientSocket.shutdownOutput();

			log.info("Acknowledged : " + json);

		 
	}

}
