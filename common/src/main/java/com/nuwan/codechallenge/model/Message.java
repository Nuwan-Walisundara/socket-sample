package com.nuwan.codechallenge.model;

public class Message {
	
	private String requestID;
	
	private String senderName;
	
	private String recipientEmail;
	
	private String senderEmail;
	
	private String subject;
	
	private String messageText;

	
	public String getSenderEmail() {
		return "walisundara2002@yahoo.com";
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Request ID:");
		stringBuilder.append(getRequestID());
		stringBuilder.append("\nSender Name:");
		stringBuilder.append(getSenderName());
		stringBuilder.append("\nRecipient Email:");
		stringBuilder.append(getRecipientEmail());
		stringBuilder.append("\nSubject:");
		stringBuilder.append(getSubject());
		stringBuilder.append("\nMessage:");
		stringBuilder.append(getMessageText());
		
		return stringBuilder.toString();
	}

}
