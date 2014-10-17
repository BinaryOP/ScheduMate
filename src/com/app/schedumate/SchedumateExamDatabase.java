// Author : Aishwarya Ajay

package com.app.schedumate;

import java.util.List;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchedumateExamDatabase extends SQLiteOpenHelper {
	
	public static final String TABLE_NAME = "Exam_Details";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_CourseNumber = "Course_Number";
	public static final String COLUMN_CourseName = "Course_Name";
	public static final String COLUMN_ExamName = "Exam_Name";
	public static final String COLUMN_DateTime = "Date_Time";
	public static final String COLUMN_Venue = "Venue";
	
	private static final String DATABASE_NAME = "exam.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_CREATE = "create table " + TABLE_NAME + "(" + 
			COLUMN_ID + " integer primary key autoincrement, " + 
			COLUMN_CourseNumber + " text not null, " +
			COLUMN_CourseName + " text not null, " +
			COLUMN_ExamName + " text not null, " +
			COLUMN_DateTime + " text not null, " +
			COLUMN_Venue + " text not null);";
	
	public SchedumateExamDatabase(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	@Override
	public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SchedumateExamDatabase.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(db);
	  }
	
	
	
	// Add Exam
	
	public void addExam(Exam exam){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_CourseNumber, exam.getCourseNumber());
		values.put(COLUMN_CourseName, exam.getCourseName());
		values.put(COLUMN_ExamName, exam.getExamName());
		values.put(COLUMN_DateTime, exam.getDate());
		values.put(COLUMN_Venue, exam.getVenue());

		long id = db.insert(TABLE_NAME, null, values);
		exam.setId(id);
		db.close();
	}
	
	// Delete
		
	public void deleteExam(Exam exam){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, COLUMN_ID+ " = " + exam.getId(),null);
	}
	
	// Return Count
	
	public int examCount(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELET * FROM" + TABLE_NAME, null);
		cursor.close();
		return cursor.getCount();
	}
	
	// Return list of Exams
	
	public List<Exam> getExams() {
		List<Exam> examList = new ArrayList<Exam>();
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		
		if(cursor.moveToFirst()) {
			do {
				Exam exam = new Exam();
				exam.setId(Integer.parseInt(cursor.getString(0)));
				exam.setCourseNumber(cursor.getString(1));
				exam.setCourseName(cursor.getString(2));
				exam.setExamName(cursor.getString(3));
				exam.setDate(cursor.getString(4));
				exam.setVenue(cursor.getString(5));
				examList.add(exam);
			} while(cursor.moveToNext());
		}
		
		return examList;
	}
}