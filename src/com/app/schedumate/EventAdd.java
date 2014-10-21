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

import java.util.List;
import java.util.ArrayList;

public class EventAdd{
	
	private static final String EVENTADD_TAG = "EventAdd";
	Context context;
	
	private SQLiteDatabase db;
	private SQLiteDatabase hw_db;
	private SQLiteDatabase events_db;
	
	private SchedumateDatabaseClass sdc_helper;
	private SchedumateHWDB shw_helper;
	private SchedumateEventDB sev_helper;
	
	private String[] columns_table = {sdc_helper.COLUMN_ID, 
			sdc_helper.COLUMN_COURSE_NAME,
			sdc_helper.COLUMN_COURSE_NUMBER,
			sdc_helper.COLUMN_COURSE_TIME,
			sdc_helper.COLUMN_COURSE_LOCATION
		};
	
	private String[] columns_hw_table = {
		shw_helper.COLUMN_HW_NAME,
		shw_helper.COLUMN_HW_DUE,
		shw_helper.COLUMN_HW_NOTE
	};
	
	private String[] columns_ev_table = {
		sev_helper.COLUMN_EVENT_NAME,
		sev_helper.COLUMN_EVENT_COMMENTS,
		sev_helper.COLUMN_EVENT_TIME
	};
	
	public EventAdd(Context context){
		sdc_helper = new SchedumateDatabaseClass(context);
		shw_helper = new SchedumateHWDB(context);
		sev_helper = new SchedumateEventDB(context);
		
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
		// Toast.makeText(context, "TEST!", Toast.LENGTH_LONG).show();
	}
	
	public void openHW() throws SQLException {
		try{
			hw_db = shw_helper.getWritableDatabase();
		}catch(Exception e){
			String errorMessage = e.getMessage();
			Log.e(EVENTADD_TAG, errorMessage);
			Toast.makeText(context, "ERROR!", Toast.LENGTH_LONG).show();
		}
		// Toast.makeText(context, "TEST!", Toast.LENGTH_LONG).show();
	}
	
	public void openEvent() throws SQLException {
		try{
			events_db = sev_helper.getWritableDatabase();
		}catch(Exception e){
			String errorMessage = e.getMessage();
			Log.e(EVENTADD_TAG, errorMessage);
			Toast.makeText(context, "ERROR!", Toast.LENGTH_LONG).show();
		}
		// Toast.makeText(context, "TEST!", Toast.LENGTH_LONG).show();
	}
	
	public void close() {
		sdc_helper.close();
	}
	
	public void closeHW() {
		shw_helper.close();
	}
	
	public void closeEvents() {
		sev_helper.close();
	}
	
	public boolean createCourse(long course_number, String course_name, String 
			location, String time) {
		boolean added = false;
		
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
		
		added = true;
		
		Cursor cursor = db.query(SchedumateDatabaseClass.TABLE_COURSE_DETAILS,
		        columns_table, SchedumateDatabaseClass.COLUMN_ID + " = " + ins_ID, null,
		        null, null, null);
		
		cursor.moveToFirst();
		CourseDetails newCourse = cursorToCourseDetails(cursor);
		cursor.close();
		
		return added;
	}
	
	public boolean createHW(String name, String due, String note){
		boolean added = false;
		ContentValues values = new ContentValues();
		
		values.put(shw_helper.COLUMN_HW_NAME, name);
		values.put(shw_helper.COLUMN_HW_DUE, due);
		values.put(shw_helper.COLUMN_HW_NOTE, note);
		
		long ins_ID = hw_db.insert(SchedumateHWDB.TABLE_HOMEWORK_DETAILS, null,
		        values);
		
		Cursor cursor = db.query(SchedumateHWDB.TABLE_HOMEWORK_DETAILS,
		        columns_hw_table, SchedumateHWDB.HW_COLUMN_ID + " = " + ins_ID, null,
		        null, null, null);
		
		added = true;
		
		cursor.moveToFirst();
		HomeworkDetails newHW = cursorToHomeworkDetails(cursor);
		cursor.close();
		
		return added;
	}
	
	public boolean createEvent(String name, String comments, String time){
		boolean added = false;
		
		ContentValues values = new ContentValues();
				
		values.put(sev_helper.COLUMN_EVENT_NAME, name);
		values.put(sev_helper.COLUMN_EVENT_COMMENTS, comments);
		values.put(sev_helper.COLUMN_EVENT_TIME, time);
		
		long ins_ID = events_db.insert(SchedumateEventDB.TABLE_EVENT_DETAILS, null,
		        values);
		
		added = true;
		
		Cursor cursor = db.query(SchedumateEventDB.TABLE_EVENT_DETAILS,
		        columns_ev_table, SchedumateEventDB.EV_COLUMN_ID + " = " + ins_ID, null,
		        null, null, null);
		
		cursor.moveToFirst();
		EventDetails newEvent = cursorToEventDetails(cursor);
		cursor.close();
		
		return added;
	}
	
	public void deleteCourse(CourseDetails course) {
		long id = course.getID();
		System.out.println("Comment deleted with id: " + id);
		db.delete(SchedumateDatabaseClass.TABLE_COURSE_DETAILS, 
				SchedumateDatabaseClass.COLUMN_ID
		    + " = " + id, null);
	}
	
	public void deleteHomework(HomeworkDetails hw) {
		long id = hw.getID();
		System.out.println("Homework deleted with id: " + id);
		hw_db.delete(SchedumateHWDB.TABLE_HOMEWORK_DETAILS, 
				SchedumateHWDB.HW_COLUMN_ID
		    + " = " + id, null);
	}
	
	public void deleteEvent(EventDetails hw) {
		long id = hw.getID();
		System.out.println("Event deleted with id: " + id);
		events_db.delete(SchedumateEventDB.TABLE_EVENT_DETAILS, 
				SchedumateEventDB.EV_COLUMN_ID
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
	
	public List<HomeworkDetails> getAllHW() {
		List<HomeworkDetails> hw = new ArrayList<HomeworkDetails>();
		
		Cursor cursor = hw_db.query(SchedumateHWDB.TABLE_HOMEWORK_DETAILS,
		    columns_hw_table, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HomeworkDetails curr_hw = cursorToHomeworkDetails(cursor);
			hw.add(curr_hw);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return hw;
	}
	
	public List<EventDetails> getAllEvents() {
		List<EventDetails> events = new ArrayList<EventDetails>();
		
		Cursor cursor = events_db.query(SchedumateEventDB.TABLE_EVENT_DETAILS,
		    columns_ev_table, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			EventDetails curr_event = cursorToEventDetails(cursor);
			events.add(curr_event);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return events;
	}
	
	private CourseDetails cursorToCourseDetails(Cursor cursor) {
	    CourseDetails cd = new CourseDetails();
	    cd.setID(cursor.getLong(0));
	    cd.setCourseNumber(cursor.getLong(
	    		cursor.getColumnIndex(SchedumateDatabaseClass.COLUMN_COURSE_NUMBER)));
		cd.setCourseName(cursor.getString(
				cursor.getColumnIndex(SchedumateDatabaseClass.COLUMN_COURSE_NAME)));
		cd.setLocation(cursor.getString(
				cursor.getColumnIndex(SchedumateDatabaseClass.COLUMN_COURSE_LOCATION)));
		cd.setTime(cursor.getString(
				cursor.getColumnIndex(SchedumateDatabaseClass.COLUMN_COURSE_TIME)));
	    return cd;
	}
	
	private HomeworkDetails cursorToHomeworkDetails(Cursor cursor){
		HomeworkDetails hd = new HomeworkDetails();
		hd.setID(cursor.getLong(0));
	    hd.setHWCourse(cursor.getString(1));
	    hd.setHWDueDate(cursor.getString(2));
	    hd.setHWNote(cursor.getString(3));
		return hd;
	}
	
	private EventDetails cursorToEventDetails(Cursor cursor){
		EventDetails ed = new EventDetails();
		ed.setID(cursor.getLong(0));
		ed.setEventName(cursor.getString(1));
		ed.setEventTime(cursor.getString(2));
		ed.setEventComments(cursor.getString(2));
		return ed;
	}
}