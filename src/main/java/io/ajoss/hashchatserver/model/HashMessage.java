package io.ajoss.hashchatserver.model;

import java.sql.Timestamp;


public class HashMessage {
	private String ID;
	private String senderID;
	private String message;
	private String deletedUserID;
	private Timestamp time;
	public HashMessage(String iD, String senderID, String message, String deletedUserID, Timestamp time) {
		super();
		ID = iD;
		this.senderID = senderID;
		this.message = message;
		this.deletedUserID = deletedUserID;
		this.time = time;
	}
	public String getID() {
		return ID;
	}
	public String getSenderID() {
		return senderID;
	}
	public String getMessage() {
		return message;
	}
	public String getDeletedUserID() {
		return deletedUserID;
	}
	public Timestamp getTime() {
		return time;
	}
	
	
}
