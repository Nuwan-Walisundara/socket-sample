package com.nu.codechallenge.server.consumer;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nu.codechallenge.server.util.ServerConFigDTO;
import com.nuwan.codechallenge.model.Message;

public class MailSender {
	private static MailSender instance;
	private ServerConFigDTO serverConfigDTO;
	final static Log logger = LogFactory.getLog(MailSender.class);

	MailSender(ServerConFigDTO config) {
		this.serverConfigDTO = config;
	}

	public void sendMail(Message msg) throws MessagingException {

		Properties props = System.getProperties();

		props.put("mail.smtp.host", serverConfigDTO.getSmptHost());
		props.put("mail.smtp.port", serverConfigDTO.getSmtpPort());
		Session session = Session.getInstance(props, null);

		MimeMessage msgmime = new MimeMessage(session);
		msgmime.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msgmime.addHeader("format", "flowed");
		msgmime.addHeader("Content-Transfer-Encoding", "8bit");
		msgmime.setFrom(new InternetAddress(msg.getSenderEmail()));
		msgmime.setReplyTo(InternetAddress.parse("no_reply@journaldev.com", false));
		msgmime.setSubject(msg.getSubject(), "UTF-8");
		msgmime.setText(msg.getMessageText(), "UTF-8");
		msgmime.setSentDate(new Date());
		msgmime.setRecipients(javax.mail.Message.RecipientType.TO,
				InternetAddress.parse(msg.getRecipientEmail(), false));
		logger.info("Message is ready");
		Transport.send(msgmime);
		logger.info("EMail Sent Successfully!!" + msg);

	}

}
