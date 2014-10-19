package com.app.schedumate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import java.util.List;

public class TestDatabaseActivity extends ListActivity {
	
	private EventAdd addCourse;
	private boolean added;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_database);
		
		addCourse = new EventAdd(this.getApplicationContext());
		addCourse.open();
		
		List<CourseDetails> courses = addCourse.getAllCourses();
		ArrayAdapter<CourseDetails> adapter = new ArrayAdapter<CourseDetails>(this, 
				android.R.layout.simple_list_item_1, courses);
		
		setListAdapter(adapter);
	}
	
	/*public boolean addCourseToDB(View view){
		added = false;
				
		String course_name = null;
		String course_number = null;
		String location = null;
		String time = null;
		
		EditText course_name_et = (EditText)findViewById(R.id.CourseNameEditText);
		course_name = course_name_et.getText().toString();
		
		EditText course_num_et = (EditText)findViewById(R.id.CourseNum);
		course_number = course_num_et.getText().toString();
		
		EditText location_et = (EditText)findViewById(R.id.LocationEditText);
		location = location_et.getText().toString();
		
		EditText time_et = (EditText)findViewById(R.id.TimeEditText);
		time = time_et.getText().toString();
		
		if( course_name == null || course_number == null 
				|| location == null || time == null ) {
			Toast.makeText(mContext, 
					"Make Sure you enter proper data in all the fields.", 
					Toast.LENGTH_LONG).show();
		}
		else {
			MainActivity.add.open();
			
			long course_num = Long.parseLong(course_number);
			added = MainActivity.add.createCourse(course_num, course_name, location, time);
			
			MainActivity.add.close();
		}
		
		return added;
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_database, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}