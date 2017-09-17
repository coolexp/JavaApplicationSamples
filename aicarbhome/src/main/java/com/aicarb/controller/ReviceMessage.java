package com.aicarb.controller;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;

public class ReviceMessage implements MessageListener {

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				ActiveMQQueue queue = (ActiveMQQueue) message.getJMSReplyTo();
				System.out.println(textMessage.getText());
			}

		} catch (Exception e) {

		}
	}

}
