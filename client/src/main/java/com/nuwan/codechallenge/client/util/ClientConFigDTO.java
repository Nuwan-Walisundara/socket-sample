package com.nuwan.codechallenge.client.util;

public class ClientConFigDTO {
	private String host;
	private int port;
	private int nuberThreads;
	private int numberOfRequests;
	
	
	
	public int getNumberOfRequests() {
		return numberOfRequests;
	}
	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}
	public int getNuberThreads() {
		return nuberThreads;
	}
	public void setNuberThreads(int nuberThreads) {
		this.nuberThreads = nuberThreads;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	

}
