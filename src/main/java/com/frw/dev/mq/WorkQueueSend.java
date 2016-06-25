package com.frw.dev.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class WorkQueueSend {

	private final static String QUEUE_NAME = "workqueue";
	public static void main(String[] args) throws IOException, TimeoutException { 
		ConnectionFactory factory=new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		for (int i = 0; i < 10; i++)
		{
			String dots = "";
			for (int j = 10; j >= i; j--)
			{
				dots += "...";
			}
			String message = "helloworld" + dots+dots.length();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
		//关闭频道和资源
		channel.close();
		connection.close();
	}

}
