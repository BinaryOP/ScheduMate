/*
 * Author : Nikhil Mehta
 * Date of Creation : 10/14/2014
 * 
 * This is the primary database class which will allow the user to score his personal schedule
 * on the phone's database.
 */

package com.app.schedumate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchedumateDatabaseClass extends SQLiteOpenHelper {
	
	public static final String TABLE_COURSE_DETAILS = "Course_Details";
	public static final String COLUMN_ID = "_ID";
	public static final String COLUMN_COURSE_NAME = "Course_Name";
	public static final String COLUMN_COURSE_NUMBER = "Course_Number";
	public static final String COLUMN_COURSE_LOCATION = "Course_Location";
	public static final String COLUMN_COURSE_TIME = "Course_Time";
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_COURSE_DETAILS + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_COURSE_NAME
		      + " text not null, " + COLUMN_COURSE_NUMBER
		      + " text not null, " + COLUMN_COURSE_LOCATION
		      + " text not null, " + COLUMN_COURSE_TIME
		      + " text not null, );";

	public SchedumateDatabaseClass(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase schedu_db) {
		// TODO Auto-generated method stub
		schedu_db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 Log.w(SchedumateDatabaseClass.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE_DETAILS);
			    onCreate(db);
	}

}
