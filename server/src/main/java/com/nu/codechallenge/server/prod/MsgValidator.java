package com.nu.codechallenge.server.prod;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nuwan.codechallenge.model.Message;
import com.nuwan.codechallenge.model.MsgResponse;

public class MsgValidator {
	Log log = LogFactory.getLog(MsgValidator.class);
	Set<String> messageIds = new HashSet<String>();

	public MsgResponse validate(Message msg) throws Exception {
		MsgResponse response = new MsgResponse(msg.getRequestID());
		log.info("validate started "+ msg);
		if(messageIds.contains(msg.getRequestID())) {
			response.setErrorMsg("Duplicate email");
			return response;
		}
		
		if(msg.getMessageText()==null || msg.getMessageText().trim().length()==0) {
			response.setErrorMsg("Empty message body");
			return response;
		}
		try {
			InternetAddress emailAddr = new InternetAddress(msg.getRecipientEmail());
			emailAddr.validate();
		} catch (AddressException ex) {
			response.setErrorMsg("Recipient Email is invalid");
			return response;
		}
		//Add to set for validating duplicates
		messageIds.add( msg.getRequestID());
		//set success if all validation pass
		response.setMsgSuccess();
		return response;
	}
}
