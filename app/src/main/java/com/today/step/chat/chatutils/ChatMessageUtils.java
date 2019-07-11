package com.today.step.chat.chatutils;

public class ChatMessageUtils {
	String message,id;
	String time,type;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {

		return type;
	}

	public ChatMessageUtils(String message, String id, String time) {
		this.message = message;
		this.id = id;
		this.time = time;
	}

	public ChatMessageUtils(String message, String id, String time, String type) {
		this.message = message;
		this.id = id;
		this.time = time;
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
