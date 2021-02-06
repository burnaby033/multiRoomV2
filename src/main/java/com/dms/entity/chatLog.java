package com.dms.entity;

public class chatLog {
	String userId;
	String message;
	int unread;
	int chatId;
	String who_read;
	
	public chatLog(String userId, String message, int unread, int chatId) {
		this.userId = userId;
		this.message = message;
		this.unread = unread;
		this.chatId = chatId;
	}

	public String getUserId() {
		return userId;
	}

	public String getMessage() {
		return message;
	}

	public int getUnread() {
		return unread;
	}

	public int getChatId() {
		return chatId;
	}

	public String getWho_read() {
		return who_read;
	}
	
	
	
}
