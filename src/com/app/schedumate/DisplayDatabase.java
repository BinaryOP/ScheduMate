package com.app.schedumate;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DisplayDatabase extends ListActivity {

	//	STRING LIST TO DISPLAY COURSE NAMES
	List<String> course_names = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_database);
		
		//	OPENING THE DATABASE.
		MainActivity.add.open();
		
		//	GETTING ALL THE COURSES.
		List<CourseDetails> values = MainActivity.add.getAllCourses();
		
		//	DISPLAYING COURSE NAMES AS A STRING LIST.
		course_names = new ArrayList<String>();
		
		//	GETTING ALL THE COURSE NAMES
		for( CourseDetails temp : values ){
			course_names.add(temp.getCourseName());
		}
		
		//	DISPLAYING THE COURSES AS AN ADAPTER
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, course_names);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_database, menu);
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
