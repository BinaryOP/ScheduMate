package com.app.schedumate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchedumateHWDB extends SQLiteOpenHelper {
	
	public static final String TABLE_HOMEWORK_DETAILS = "HW_details";
	public static final String HW_COLUMN_ID = "_ID";
	public static final String COLUMN_HW_NAME = "Course_hw_name";
	public static final String COLUMN_HW_DUE = "Hw_Due";
	public static final	String COLUMN_HW_NOTE = "Hw_note";
	public static final String DATABASE_NAME = "course_details.db";
	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_CREATE = "create table "
		      + TABLE_HOMEWORK_DETAILS + "(" + HW_COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_HW_NAME
		      + " text, " + COLUMN_HW_DUE
		      + " text, " + COLUMN_HW_NOTE
		      + " text);";

	public SchedumateHWDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase schedu_hw_db) {
		// TODO Auto-generated method stub
		schedu_hw_db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 Log.w(SchedumateDatabaseClass.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
			    db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMEWORK_DETAILS);
			    onCreate(db);
	}

	
}
