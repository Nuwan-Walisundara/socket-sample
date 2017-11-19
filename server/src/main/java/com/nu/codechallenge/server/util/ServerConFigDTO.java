package com.nu.codechallenge.server.util;

import java.io.Serializable;

public class ServerConFigDTO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4053048561814987406L;
	
	private int serverPort;
	private int emailQueueSize;
	private String smptHost = "localhost";
	private int smtpPort  ;
	private int numberOfProducers;
	private int numberOfConsumers;
	
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public int getEmailQueueSize() {
		return emailQueueSize;
	}
	public void setEmailQueueSize(int emailQueueSize) {
		this.emailQueueSize = emailQueueSize;
	}
	public String getSmptHost() {
		return smptHost.trim();
	}
	public void setSmptHost(String smptHost) {
		this.smptHost = smptHost;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public int getNumberOfProducers() {
		return numberOfProducers;
	}
	public void setNumberOfProducers(int numberOfProducers) {
		this.numberOfProducers = numberOfProducers;
	}
	public int getNumberOfConsumers() {
		return numberOfConsumers;
	}
	public void setNumberOfConsumers(int numberOfConsumers) {
		this.numberOfConsumers = numberOfConsumers;
	}
	@Override
	public String toString() {
		return "ServerConFigDTO [serverPort=" + serverPort + ", emailQueueSize=" + emailQueueSize + ", smptHost="
				+ smptHost + ", smtpPort=" + smtpPort + ", numberOfProducers=" + numberOfProducers
				+ ", numberOfConsumers=" + numberOfConsumers + "]";
	}
	 
	

	
	
}
