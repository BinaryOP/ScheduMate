package com.app.schedumate;

public class CourseDetails {
	private long id;
	private long courseNumber;
	private String courseName;
	private String location;
	private String time;

	public long getID() {
		return id;
	}

	public void setID(long id) {
		this.id = id;
	}


	public long getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(long courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
	  return courseName;
	}

	public void setCourseName(String courseName) {
	  this.courseName = courseName;
	}


	public String getLocation() {
		  return location;
		}

		public void setLocation(String location) {
		  this.location = location;
		}

		public String getTime() {
			  return time;
			}

			public void setTime(String time) {
			  this.time = time;
			}
	
	// Will be used by the ArrayAdapter in the ListView
	  @Override
	public String toString() {
	  return courseName;
	}
}
