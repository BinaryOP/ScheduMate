// Author : Aishwarya Ajay

package com.app.schedumate;

public class Exam{
	private long dbId;
	private String courseNumber;
	private String courseName;
	private String examName;
	private String dateTime;
	private String venue;
	
	public long getId() {
		return this.dbId;
	}
	
	public void setId(long id){
		this.dbId = id;
	}
	
	public String getCourseNumber() {
		return this.courseNumber;
	}

	public void setCourseNumber(String courseN) {
		this.courseNumber = courseN;
	}

	public String getCourseName(){
		return this.courseName;
	}
	
	public void setCourseName(String course){
		this.courseName = course;
	}
	
	public String getExamName(){
		return this.examName;
	}
	
	public void setExamName(String exam){
		this.examName = exam;
	}
	
	public String getDate(){
		return this.dateTime;
	}
	
	public void setDate(String date){
		this.dateTime = date;
	}
	
	public String getVenue(){
		return this.venue;
	}
	
	public void setVenue(String venue){
		this.venue = venue;
	}
	
	@Override
	public String toString(){
		return courseName+" "+examName+" "+dateTime+" "+venue;
	}
}
