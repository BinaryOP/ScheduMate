/*
 *  *						
 * 
 */

package com.app.schedumate;

// TEST

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class MainActivity extends ActionBarActivity implements OnClickListener, 
	ConnectionCallbacks, OnConnectionFailedListener {
	
	boolean open;
	protected static EventAdd add;
	
	//G+ Sign in 
	private static final int RC_SIGN_IN = 0;
	private boolean mSignInClicked;
	private boolean mIntentInProgress;
	private ConnectionResult mConnectionResult;
	//G+ Sign in button
	private SignInButton btnSignIn;
	private Button btnSignOut;
	private TextView txtName;
	
	//Google API
	private GoogleApiClient mGoogleApiClient;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        //G+
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        txtName = (TextView) findViewById(R.id.txtName);
        
        btnSignIn.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        //API Client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener((com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) this).addApi(Plus.API, null)
        .addScope(Plus.SCOPE_PLUS_LOGIN).build();
        
        //G+--end
        add = new EventAdd(this.getApplicationContext());*/
    }
    
    public void onClickAdd(View view) {
    	open = false;
    	
    	Intent test_db = new Intent(this, TestDatabaseActivity.class);
    	startActivity(test_db);
    }
    
    public void onClickDisplayList(View view) {
    	
    	Intent disp_db = new Intent(this, DisplayDatabase.class);
    	startActivity(disp_db);
    }
    
    public void onClickDisplayConnectivityStatus(View view){
    	CheckConnectivity status = new CheckConnectivity(this.getApplicationContext());
    	
    	String status_connection = "Not Connected";
    	
    	boolean status_conn = status.returnConnectivityStatus();
    	if( status_conn )
    		status_connection = "Connected";
    	
    	Toast.makeText(this.getApplicationContext(), status_connection, Toast.LENGTH_LONG).show();
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

    
    //Google+ Sign In Code - Aishwarya Ajay
    //Code Reference for G+ SignIn : "Android Login with Google Plus Account" - Ravi Tamada - AndroidAuthority.com

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_sign_in:
            // Signin button clicked
            signInWithGplus();
            break;
        case R.id.btn_sign_out:
            // Signout button clicked
            signOutFromGplus();
            break;
        }
    }
    
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }
     
        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;
     
            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
     
    }
     
    @Override
    protected void onActivityResult(int requestCode, int responseCode,
            Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }
     
            mIntentInProgress = false;
     
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }
     
    @Override
    public void onConnected(Bundle arg0) {
        mSignInClicked = false;
        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
     
        // Get user's information
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            txtName.setText("Hello "+ currentPerson.getDisplayName());
        }
        else {
        	txtName.setText("Hello");
        }
        // Update the UI after signin
        updateUI(true);
    }
     
    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
        updateUI(false);
    }
     
    //Update UI
    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            txtName.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            txtName.setVisibility(View.GONE);
        }
    }
    
    //SignIn
    private void signInWithGplus() {
        if (!mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            resolveSignInError();
        }
    }
    
    //Singout
    private void signOutFromGplus() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            updateUI(false);
        }
    }
    
    //Resolve Signin Errors
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }
    
    // -- End - Google+ Sign In - Aishwarya Ajay
}
