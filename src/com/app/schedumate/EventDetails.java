package com.app.schedumate;

public class EventDetails {
	
	private long id;
	private String event_name;
	private String event_comments;
	private String event_time;
	
	public void setID(long id) {
		this.id = id;
	}
	
	public long getID(){
		return this.getID();
	}
	
	public void setEventName(String event_name){
		this.event_name = event_name;
	}
	
	public String getEventName(){
		return this.event_name;
	}
	
	public void setEventComments(String event_comments){
		this.event_comments = event_comments;
	}
	
	public String getEventComments(){
		return this.event_comments;
	}
	
	public void setEventTime(String time){
		this.event_time = time;
	}
	
	public String getEventTime(){
		return this.event_time;
	}

}
