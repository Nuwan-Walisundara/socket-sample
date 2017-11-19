package com.nu.codechallenge.server.prod;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;
import com.nuwan.codechallenge.model.Message;

public class MsgMarshaller {
	Log log = LogFactory.getLog(MsgMarshaller.class);

	public Message marshall(Socket clientSocket) throws IOException {
		  BufferedReader in = null;
		  in = new BufferedReader(new 
			        InputStreamReader( clientSocket.getInputStream()));
		  
		  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int readBytes = -1;
			StringBuilder sb =new StringBuilder();
			
		    while(in.ready()){
		      try{
		    	  sb.append(  in.readLine());
		//Send data back to client
		        
		//Append data to text area
		       
		       }catch (IOException e) {
		        System.out.println("Read failed");
		        
		       }
		    }
		  
	/*	InputStream inputStream = clientSocket.getInputStream();
		

		while ((readBytes = inputStream.read(buffer)) > 1) {
			byteArrayOutputStream.write(buffer, 0, readBytes);
		}
*/
		String jsonRequest = byteArrayOutputStream.toString();
		byteArrayOutputStream.close();
		clientSocket.shutdownInput();

		log.info("Message Received : " + jsonRequest);

		Gson gsonParser = new Gson();
		return gsonParser.fromJson(sb.toString() , Message.class);
	}

}
