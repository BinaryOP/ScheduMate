package com.app.schedumate;
import android.content.BroadcastReceiver;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

public class NotifyReceiver extends BroadcastReceiver { 

    @Override
    public void onReceive(Context context, Intent intent){
        NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification();
        notification.tickerText = "This is your notification.";
        nm.notify(0, notification);
    }
    
}
