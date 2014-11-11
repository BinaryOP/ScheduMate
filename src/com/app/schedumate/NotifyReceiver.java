package com.app.schedumate;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class NotifyReceiver extends Service { 
        
 @Override
public void onCreate() {   
	    // When notification is selected intent will return user to main screen.
	    Intent intent = new Intent(this, MainActivity.class);
	    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

	    // Build notification
	    Notification noti = new Notification.Builder(this)
	        .setContentTitle("This was your notification!")
	        .setContentText("Subject").setSmallIcon(R.drawable.abc_btn_check_material)
	        .setContentIntent(pIntent).build();
	    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	    // hide the notification after its selected
	    noti.flags |= Notification.FLAG_AUTO_CANCEL;
	    notificationManager.notify(0, noti);
    }

@Override
public IBinder onBind(Intent intent) {
// TODO Auto-generated method stub
Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
return null;
}

}