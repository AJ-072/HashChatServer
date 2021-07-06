package io.ajoss.hashchatserver.model;

import java.sql.Timestamp;

public class HashUserInbox {
	private String ID;
	private String userID;
	private String senderID;
	private String chatID;
	private String lastMsg;
	private int unseenMsgs;
	private boolean deleted;
	private String data;
	private Timestamp time;
	
	public HashUserInbox(String iD, String userID, String senderID, String chatID, String lastMsg, int unseenMsgs,
			boolean deleted, String data, Timestamp time) {
		super();
		ID = iD;
		this.userID = userID;
		this.senderID = senderID;
		this.chatID = chatID;
		this.lastMsg = lastMsg;
		this.unseenMsgs = unseenMsgs;
		this.deleted = deleted;
		this.data = data;
		this.time = time;
	}

	public String getID() {
		return ID;
	}

	public String getUserID() {
		return userID;
	}

	public String getSenderID() {
		return senderID;
	}

	public String getChatID() {
		return chatID;
	}

	public String getLastMsg() {
		return lastMsg;
	}

	public int getUnseenMsgs() {
		return unseenMsgs;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public String getData() {
		return data;
	}

	public Timestamp getTime() {
		return time;
	}
	
	
}
