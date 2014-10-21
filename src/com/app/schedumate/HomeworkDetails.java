package com.app.schedumate;

public class HomeworkDetails {
	
	private long id;
	private String hw_course;
	private String hw_due;
	private String hw_note;
	
	public void setID(long id) {
		this.id = id;
	}
	
	public long getID(){
		return this.id;
	}
	
	public void setHWCourse(String course){
		this.hw_course = course;
	}
	
	public String getHWCourse(){
		return this.hw_course;
	}
	
	public void setHWDueDate(String due){
		this.hw_due = due;
	}
	
	public String getHWDueDate(){
		return this.hw_due;
	}
	
	public void setHWNote(String note){
		this.hw_note = note;
	}
	
	public String getHWNote(){
		return this.hw_note;
	}
}