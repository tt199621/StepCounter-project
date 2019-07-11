package com.today.step.chat;

public class ChatMessageEvent {
	String order_id,amount,moeny;

	public ChatMessageEvent(String order_id, String amount, String moeny) {
		this.order_id = order_id;
		this.amount = amount;
		this.moeny = moeny;
	}
}
