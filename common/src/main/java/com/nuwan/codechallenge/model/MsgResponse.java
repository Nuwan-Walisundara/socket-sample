package com.nuwan.codechallenge.model;

public class MsgResponse {
	
	private String requestID;
	
	private String status;

	private static String STATUS_OK="OK";
	private static String STATUS_ERROR="ERROR";
	private String errorMsg;
	
	public MsgResponse(String requestID) {
		this.requestID = requestID;
	}
	public void setErrorMsg(String msg) {
		this.status=STATUS_ERROR;
		errorMsg=msg;
	}
	
	public String getRequestID() {
		return requestID;
	}
 

	public void setMsgSuccess() {
		this.status = STATUS_OK;
	}
	
	public void setStatus(String status){
		this.status =status;
	}
	
	
	@Override
	public String toString() {
		return "MsgResponse [requestID=" + requestID + ", status=" + status + "]";
	}
	
	

}
