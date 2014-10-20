package com.app.schedumate;

// TEST

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Intent;

import java.util.List;
import java.util.Random;

public class MainActivity extends ActionBarActivity {
	
	boolean open;
	protected static EventAdd add;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        add = new EventAdd(this.getApplicationContext());
    }
    
    public void onClickAdd(View view) {
    	open = false;
    	
    	Intent test_db = new Intent(this, TestDatabaseActivity.class);
    	startActivity(test_db);
    }

    // hello test commit
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    //Action handler for feedback button on first screen.
    //Opens SubmitFeedBackActivity in App.
    public void sendFeedback(View view){
    	 	Intent intent = new Intent(this, FeedBackActivity.class);
    	    startActivity(intent);
    }
    //Action handler for set reminder test button on first screen.
    //Opens SetReminderActivity in App.
    public void setReminder(View view){
    	 	Intent intent = new Intent(this, SetReminderActivity.class);
    	    startActivity(intent);
    }
    
}
