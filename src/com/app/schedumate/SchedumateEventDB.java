package com.app.schedumate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchedumateEventDB extends SQLiteOpenHelper {
	
	public static final String TABLE_EVENT_DETAILS = "Event_Details";
	public static final String EV_COLUMN_ID = "_ID";
	public static final String COLUMN_EVENT_NAME = "Event_Name";
	public static final String COLUMN_EVENT_COMMENTS = "Event_comments";
	public static final String COLUMN_EVENT_TIME = "Event_time";
	public static final String DATABASE_NAME = "course_details.db";
	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_EVENT_DETAILS + "(" + EV_COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_EVENT_NAME
		      + " text, " + COLUMN_EVENT_COMMENTS
		      + " text, " + COLUMN_EVENT_TIME
		      + " text);";

	public SchedumateEventDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase event_db) {
		// TODO Auto-generated method stub
		event_db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(SchedumateDatabaseClass.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT_DETAILS);
		    onCreate(db);
	}
}