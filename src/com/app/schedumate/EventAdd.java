/*
 * EventAdd Class - A CLASS THAT HELPS USERS ADDS CLASSES/EVENTS TO
 * THE DATABSE
 * 
 * AUTHOR :- ARITRA SAMANTA
 * 
 * CREATED ON - 10/14/2014
 */

package com.app.schedumate;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;
import android.widget.Toast;
import android.view.View;

import java.util.List;
import java.util.ArrayList;

public class EventAdd{
	
	private static final String EVENTADD_TAG = "EventAdd";
	Context context;
	
	private SQLiteDatabase db;
	private SchedumateDatabaseClass sdc_helper;
	private String[] columns_table = {sdc_helper.COLUMN_ID, 
			sdc_helper.COLUMN_COURSE_NAME,
			sdc_helper.COLUMN_COURSE_NUMBER,
			sdc_helper.COLUMN_COURSE_TIME,
			sdc_helper.COLUMN_COURSE_LOCATION
		};
	
	public EventAdd(Context context){
		sdc_helper = new SchedumateDatabaseClass(context);
		this.context = context;
	}
	
	public void open() throws SQLException {
		try{
			db = sdc_helper.getWritableDatabase();
		}catch(Exception e){
			String errorMessage = e.getMessage();
			Log.e(EVENTADD_TAG, errorMessage);
			Toast.makeText(context, "ERROR!", Toast.LENGTH_LONG).show();
		}
		Toast.makeText(context, "TEST!", Toast.LENGTH_LONG).show();
	}
	
	public void close() {
		sdc_helper.close();
	}
	
	public CourseDetails createCourse(long course_number, String course_name, String 
			location, String time) {
		ContentValues values = new ContentValues();
		CourseDetails cd = new CourseDetails();
		
		cd.setCourseNumber(course_number);
		cd.setCourseName(course_name);
		cd.setLocation(location);
		cd.setTime(time);
		
		values.put(sdc_helper.COLUMN_COURSE_NAME, cd.getCourseName());
		values.put(sdc_helper.COLUMN_COURSE_NUMBER, cd.getCourseNumber());
		values.put(sdc_helper.COLUMN_COURSE_LOCATION, cd.getLocation());
		values.put(sdc_helper.COLUMN_COURSE_TIME, cd.getTime());
		
		long ins_ID = db.insert(SchedumateDatabaseClass.TABLE_COURSE_DETAILS, null,
		        values);
		
		Cursor cursor = db.query(SchedumateDatabaseClass.TABLE_COURSE_DETAILS,
		        columns_table, SchedumateDatabaseClass.COLUMN_ID + " = " + ins_ID, null,
		        null, null, null);
		cursor.moveToFirst();
		CourseDetails newCourse = cursorToCourseDetails(cursor);
		cursor.close();
		
		return newCourse;
	}
	
	public void deleteCourse(CourseDetails course) {
		long id = course.getID();
		System.out.println("Comment deleted with id: " + id);
		db.delete(SchedumateDatabaseClass.TABLE_COURSE_DETAILS, 
				SchedumateDatabaseClass.COLUMN_ID
		    + " = " + id, null);
	 }
	
	public List<CourseDetails> getAllCourses() {
		List<CourseDetails> courses = new ArrayList<CourseDetails>();
		
		Cursor cursor = db.query(SchedumateDatabaseClass.TABLE_COURSE_DETAILS,
		    columns_table, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CourseDetails curr_course = cursorToCourseDetails(cursor);
			courses.add(curr_course);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return courses;
	  }
	
	private CourseDetails cursorToCourseDetails(Cursor cursor) {
	    CourseDetails cd = new CourseDetails();
	    cd.setID(cursor.getLong(0));
	    cd.setCourseNumber(cursor.getLong(1));
		cd.setCourseName(cursor.getString(1));
		cd.setLocation(cursor.getString(0));
		cd.setTime(cursor.getString(1));
	    return cd;
	}
}