/*
 * EventAdd Class - A CLASS THAT HELPS USERS ADDS CLASSES/EVENTS TO
 * THE DATABSE
 * 
 * AUTHOR :- ARITRA SAMANTA
 * 
 * CREATED ON - 10/14/2014
 */

package com.app.schedumate;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class EventAdd {
	
	private SQLiteDatabase db;
	private SchedumateDatabaseClass sdc_helper;
	private String[] columns_table = {sdc_helper.COLUMN_ID, 
			sdc_helper.COLUMN_COURSE_NAME,
			sdc_helper.COLUMN_COURSE_NUMBER,
			sdc_helper.COLUMN_COURSE_TIME,
			sdc_helper.COLUMN_COURSE_LOCATION
		};
			
	
	
	public EventAdd(Context context)
	{
		sdc_helper = new SchedumateDatabaseClass();
	}
}
