package com.nuwan.codechallenge.client.util;

import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nuwan.codechallenge.model.Message;



public class MailUtil   {
	
	final static Log logger = LogFactory.getLog( MailUtil.class);

	
	 
	
	public static Message   buildMessage( ) {
		Message msg = new Message();
		msg.setRequestID( UUID.randomUUID().toString());
		msg.setSubject("test");
		msg.setRecipientEmail("nuwan.walisundara@gmail.com");
		msg.setSenderName("Nuwan Walisundara");
		msg.setMessageText("Sample  message" + msg.getRequestID());

		return msg;
	}
 
}
