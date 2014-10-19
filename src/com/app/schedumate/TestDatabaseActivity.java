package com.app.schedumate;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.app.ListActivity;

import java.util.List;

public class TestDatabaseActivity extends ListActivity {
	
	private EventAdd addCourse;
	private boolean added;

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
	
	public boolean addCourseToDB(){
		added = false;
		return added;
	}

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
