package com.app.schedumate;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import 	android.widget.TimePicker;
import android.widget.Toast;


public class SetReminderActivity extends ActionBarActivity {
    private DatePicker picker;
    private TimePicker timepicker;
	private PendingIntent pendingIntent;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_reminder);
	        picker = (DatePicker) findViewById(R.id.scheduleTimePicker);
	        timepicker = (TimePicker) findViewById(R.id.scheduleTimePicker2);
	    }

    public void onDateSelectedButtonClick(View v){
        int day = picker.getDayOfMonth();
        int month = picker.getMonth();
        int year = picker.getYear();
        
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        c.set(Calendar.HOUR_OF_DAY, timepicker.getCurrentHour());
        c.set(Calendar.MINUTE, timepicker.getCurrentMinute());
        c.set(Calendar.SECOND, 0);
        

       Toast.makeText(this, "Notification set for: "+ (month+1)+"/"+ day +"/"+ year + " at " + timepicker.getCurrentHour() + ":" + timepicker.getCurrentMinute() , Toast.LENGTH_SHORT).show();
       
 	  Intent myIntent = new Intent(SetReminderActivity.this, NotifyReceiver.class);
 	  pendingIntent = PendingIntent.getService(SetReminderActivity.this, 0, myIntent, 0);
 	  AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
 	  alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.set_reminder, menu);
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
