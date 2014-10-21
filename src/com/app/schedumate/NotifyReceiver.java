package com.app.schedumate;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

public class NotifyReceiver extends Service { 
        
 @Override
public void onCreate() {   
    	Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
    }
    
@Override
    public IBinder onBind(Intent intent) {
    	return null;
    }
    
}
