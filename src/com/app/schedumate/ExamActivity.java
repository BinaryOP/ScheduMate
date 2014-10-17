// Author : Aishwarya Ajay

package com.app.schedumate;

import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;


public class ExamActivity extends ActionBarActivity{

	private SchedumateExamDatabase database;
	private ListView lv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		database = new SchedumateExamDatabase(this);
		
		List<Exam> exams = database.getExams();
		
		ArrayAdapter<Exam> adapter = new ArrayAdapter<Exam>(this,android.R.layout.simple_list_item_1,exams);
		lv = (ListView) findViewById(android.R.id.list);
		lv.setAdapter(adapter);
	}
	
	
}