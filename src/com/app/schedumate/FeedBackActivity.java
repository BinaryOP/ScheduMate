/*
 * Author: Christopher Hsieh
 * Written: 10/14/2014
 * 
 * This class will take in user input and ensure the text is valid. 
 * Once verified the input is valid we will allow the input to be submitted. 
 * This class will then email the input/feedback to our team.
 */

package com.app.schedumate;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FeedBackActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_back);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed_back, menu);
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

	 public void sendFeedBackBtn(View view){
		 EditText editText = (EditText) findViewById(R.id.feedBackTextBox);
		 String message = editText.getText().toString();
		 
		 //make sure there is input
		 if(message.length() > 1){
			 TextView t = (TextView)findViewById(R.id.textView1); 
			 
			 Intent i = new Intent(Intent.ACTION_SEND);
			 i.setType("message/rfc822");
			 i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"chrishsieh@live.com"});
			 i.putExtra(Intent.EXTRA_SUBJECT, "Feedback Subject");
			 i.putExtra(Intent.EXTRA_TEXT   , message);
			 try {
			     startActivity(Intent.createChooser(i, "Send mail..."));
			     t.setText("Thank your for your feedback.");
			 } catch (android.content.ActivityNotFoundException ex) {
				t.setText("Email Failed to send");
			 }
		 }
 }
}
