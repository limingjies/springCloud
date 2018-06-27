package com.fortunebill.springBoot.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.ExchangeTypes;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerForExchange {
	private static String exchangeName = "direct";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.1.50");
		connectionFactory.setPort(5672);
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("admin");
		Connection newConnection = connectionFactory.newConnection();
		Channel channel = newConnection.createChannel();
		channel.exchangeDeclare(exchangeName, ExchangeTypes.DIRECT);
		for (int i = 0; i < 10; i++) {
			String msg = "woshi--" + i + "--";
			channel.basicPublish(exchangeName, "", null, msg.getBytes());
			System.out.println("sent msg :" + msg);
		}
		channel.close();
		newConnection.close();
	}
}
