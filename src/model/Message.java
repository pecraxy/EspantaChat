package model;

public class Message {
	private final User messageSender;
	private final String msg;
	public final static boolean END_CONNECTION = true;
	
	public Message(User messageSender, String msg) {
		this.messageSender = messageSender;
		this.msg = msg;
	}
	
	public User getUser() {
		return messageSender;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	@Override
	public String toString() {
		return this.getUser().getNickname() + ": " + this.getMsg();
	}
}
