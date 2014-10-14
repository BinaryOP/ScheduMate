package com.app.schedumate;

public class CourseDetails {
	private long id;
	private long courseNumber;
	private String courseName;
	private String location;
	private String time;

	// get ID
	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}

//get the course number
	public long getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(long courseNumber) {
		this.courseNumber = courseNumber;
	}
// get the course name
	public String getCourseName() {
	  return courseName;
	}

	public void setCourseName(String courseName) {
	  this.courseName = courseName;
	}

// get location of class
	public String getLocation() {
		  return location;
	}

	public void setLocation(String location) {
	  this.location = location;
	}
// get time of class
	public String getTime() {
		  return time;
	}

	public void setTime(String time) {
		  this.time = time;
	}
	
	
	  @Override
	public String toString() {
	  return courseName;
	}
}
